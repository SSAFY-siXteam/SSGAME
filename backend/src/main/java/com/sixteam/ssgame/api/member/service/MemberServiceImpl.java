package com.sixteam.ssgame.api.member.service;

import com.sixteam.ssgame.api.analysis.repository.CategoryRepository;
import com.sixteam.ssgame.api.analysis.repository.MemberFrequentGenreRepository;
import com.sixteam.ssgame.api.analysis.repository.RadarChartInfoRepository;
import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import com.sixteam.ssgame.api.gameInfo.entity.MemberGameList;
import com.sixteam.ssgame.api.gameInfo.repository.GameInfoRepository;
import com.sixteam.ssgame.api.gameInfo.repository.MemberGameListRepository;
import com.sixteam.ssgame.api.gameInfo.repository.MemberPreferredTagRepository;
import com.sixteam.ssgame.api.member.dto.MemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestMemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestUpdateMemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestUpdateMemberSteamIDDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseMemberDto;

import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.entity.MemberPreferredCategory;
import com.sixteam.ssgame.api.member.repository.MemberPreferredCategoryRepository;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
import com.sixteam.ssgame.api.recommendation.repository.RecommendedGameRepository;
import com.sixteam.ssgame.global.common.steamapi.SteamAPIScrap;
import com.sixteam.ssgame.global.error.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sixteam.ssgame.global.error.dto.ErrorStatus.*;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final MemberPreferredCategoryRepository memberPreferredCategoryRepository;

    private final CategoryRepository categoryRepository;

    private final MemberGameListRepository memberGameListRepository;

    private final GameInfoRepository gameInfoRepository;

    private final RecommendedGameRepository recommendedGameRepository;

    private final MemberPreferredTagRepository memberPreferredTagRepository;

    private final MemberFrequentGenreRepository memberFrequentGenreRepository;

    private final RadarChartInfoRepository radarChartInfoRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean hasSsgameId(String ssgameId) {
        return memberRepository.existsBySsgameId(ssgameId);
    }

    @Override
    public boolean hasEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public boolean hasSteamID(String steamID) {
        return memberRepository.existsBySteamID(steamID);
    }

    @Override
    @Transactional
    public boolean register(RequestMemberDto requestMemberDto) throws IOException, ParseException {
        String encryptedPassword = passwordEncoder.encode(requestMemberDto.getPassword());
        log.debug("패스워드 암호화 " + encryptedPassword);

        Map<String, Object> steamAPIMemberData = new HashMap<>();
        Map<String, Object> steamAPIGameData = new HashMap<>();

        String steamID = requestMemberDto.getSteamID();

        steamAPIMemberData = SteamAPIScrap.getMemberData(steamID);
        steamAPIGameData = SteamAPIScrap.getGameData(steamID);

        Member savedMember = memberRepository.save(Member.builder()
                .ssgameId(requestMemberDto.getSsgameId())
                .password(encryptedPassword)
                .email(requestMemberDto.getEmail())
                .steamID(steamID)
                .steamNickname((String) steamAPIMemberData.get("steamNickname"))
                .avatarUrl((String) steamAPIMemberData.get("avatarUrl"))
                .isPublic((boolean) steamAPIGameData.get("isPublic"))
                .gameCount((Long) steamAPIGameData.get("gameCount"))
                .isDeleted(false)
                .build());

        List<String> preferredCategories = requestMemberDto.getPreferredCategories();
        for (String preferredCategory : preferredCategories) {
            memberPreferredCategoryRepository.save(MemberPreferredCategory.builder()
                    .member(savedMember)
                    .category(categoryRepository.findByCategoryName(preferredCategory))
                    .build());
        }

        Map<Long, Long> memberGameList = (Map<Long, Long>) steamAPIGameData.get("memberGameList");

        if (memberGameList.size() != 0) {
            for (Long steamAppid: memberGameList.keySet()) {
                GameInfo gameInfo = gameInfoRepository.findBySteamAppid(steamAppid);
                // steam app id에 해당하는 게임 저장
                if (gameInfo == null) {
                    continue;
                }
                // 회원가입 하고 나서 수정할 때는... 또 다른 로직이 필요함...
                // 새로 추가한 게임이나 기존 게임에서 플레이 시간만 업데이트 하는 로직
                memberGameListRepository.save(MemberGameList.builder()
                        .member(savedMember)
                        .gameInfo(gameInfo)
                        .memberPlayTime(memberGameList.get(steamAppid))
                        .build());
            }
        }
        return savedMember.getIsPublic();
    }

    @Override
    public MemberDto findMemberDtoBySsggameId(String ssgameId) {

        Member member = memberRepository.findBySsgameId(ssgameId);
        if (member == null) {
            throw new CustomException("cannot find member by " + ssgameId, SSGAMEID_NOT_FOUND);
        }

        List<MemberPreferredCategory> categories = memberPreferredCategoryRepository.findAllByMember(member);

        List<String> preferredCategories = new ArrayList<>();
        for (MemberPreferredCategory category : categories) {
            preferredCategories.add(category.getCategory().getCategoryName());
        }

        return MemberDto.builder()
                .memberSeq(member.getMemberSeq())
                .ssgameId(member.getSsgameId())
                .password(member.getPassword())
                .email(member.getEmail())
                .steamID(member.getSteamID())
                .steamNickname(member.getSteamNickname())
                .avatarUrl(member.getAvatarUrl())
                .isPublic(member.getIsPublic())
                .gameCount(member.getGameCount())
                .preferredCategories(preferredCategories)
                .build();
    }

    @Override
    public ResponseMemberDto findResponseMemberDtoBySsgameId(String ssgameId) {

        MemberDto member = findMemberDtoBySsggameId(ssgameId);

        return ResponseMemberDto.builder()
                .ssgameId(member.getSsgameId())
                .email(member.getEmail())
                .steamID(member.getSteamID())
                .steamNickname(member.getSteamNickname())
                .avatarUrl(member.getAvatarUrl())
                .gameCount(member.getGameCount())
                .preferredCategories(member.getPreferredCategories())
                .build();
    }

    @Override
    public Member findMemberBySsgameId(String ssgameId) {
        return memberRepository.findBySsgameId(ssgameId);
    }

    @Transactional
    @Override
    public void updateMember(String ssgameId, RequestUpdateMemberDto requestUpdateMemberDto) {

        Member member = memberRepository.findBySsgameId(ssgameId);
        if (member == null) {
            throw new CustomException("cannot find member by " + ssgameId, SSGAMEID_NOT_FOUND);
        }

        String password = member.getPassword();
        if (!passwordEncoder.matches(requestUpdateMemberDto.getPrePassword(), password)) {
            throw new CustomException("password not matches in update member", PASSWORD_NOT_MATCH);
        }
        String newPassword = requestUpdateMemberDto.getNewPassword();
        if (newPassword != null) {
            password = passwordEncoder.encode(newPassword);
        }

        String email = requestUpdateMemberDto.getEmail();
        if (!member.getEmail().equals(email) && hasEmail(email)) {
            throw new CustomException("email duplication in update member", EMAIL_DUPLICATION);
        }
        member.changeMember(password, email);

        // 회원 선호 카테고리 변경
        if (requestUpdateMemberDto.getIsCategoryChanged()) {
            memberPreferredCategoryRepository.deleteAllByMember(member);
            for (String categoryName : requestUpdateMemberDto.getPreferredCategories()) {
                memberPreferredCategoryRepository.save(MemberPreferredCategory.builder()
                        .member(member)
                        .category(categoryRepository.findByCategoryName(categoryName))
                        .build());
            }
        }
    }

    @Transactional
    @Override
    public void updateMemberSteamID(String ssgameId, RequestUpdateMemberSteamIDDto requestUpdateMemberSteamIDDto) {

        Member member = memberRepository.findBySsgameId(ssgameId);
        if (member == null) {
            throw new CustomException("cannot find member by " + ssgameId, SSGAMEID_NOT_FOUND);
        }

        String password = member.getPassword();
        if (!passwordEncoder.matches(requestUpdateMemberSteamIDDto.getPassword(), password)) {
            throw new CustomException("password not matches in update member", PASSWORD_NOT_MATCH);
        }

        String steamID = requestUpdateMemberSteamIDDto.getSteamID();
        if (!member.getSteamID().equals(steamID) && hasSteamID(steamID)) {
            throw new CustomException("steamID duplication in update member", STEAMID_DUPLICATION);
        }

        /**
         * 변경되는 테이블
         * 1. 사용자 게임
         * 2. 사용자 추천 게임
         * 3. 사용자 선호 태그
         * 4. 사용자가 가장 많이 플레이한 장르
         * 5. 분석그래프 정보
         */

        // 기존 steamID에 관련된 내용 삭제
        memberGameListRepository.deleteAllByMember(member);
        recommendedGameRepository.deleteAllByMember(member);
        memberPreferredTagRepository.deleteAllByMember(member);
        memberFrequentGenreRepository.deleteAllByMember(member);
        radarChartInfoRepository.deleteAllByMember(member);

        // steamID 변경
        member.changeMemberSteamID(steamID);

        // 새로운 steamID로 재등록
        

        // 새로운 steamID.isPublic == false면 에러 반환

    }
}
