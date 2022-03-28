package com.sixteam.ssgame.api.member.service;

import com.sixteam.ssgame.api.game.entity.Game;
import com.sixteam.ssgame.api.game.entity.MemberGameList;
import com.sixteam.ssgame.api.game.repository.GameRepository;
import com.sixteam.ssgame.api.game.repository.MemberGameListRepository;
import com.sixteam.ssgame.api.analysis.repository.CategoryRepository;
import com.sixteam.ssgame.api.member.dto.request.RequestMemberDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseLoginMemberDto;
import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.entity.MemberPreferredCategory;
import com.sixteam.ssgame.api.member.repository.MemberPreferredCategoryRepository;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
import com.sixteam.ssgame.global.common.steamapi.APIConnectionException;
import com.sixteam.ssgame.global.common.steamapi.SteamAPIScrap;
import com.sixteam.ssgame.global.error.exception.EntityNotFoundException;
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

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final MemberPreferredCategoryRepository memberPreferredCategoryRepository;

    private final CategoryRepository categoryRepository;

    private final MemberGameListRepository memberGameListRepository;

    private final GameRepository gameRepository;

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
    public void register(RequestMemberDto requestMemberDto) {
        String encryptedPassword = passwordEncoder.encode(requestMemberDto.getPassword());
        log.debug("패스워드 암호화 " + encryptedPassword);

        Map<String, Object> steamAPIMemberData = new HashMap<>();
        Map<String, Object> steamAPIGameData = new HashMap<>();

        String steamID = requestMemberDto.getSteamID();
        try {
            steamAPIMemberData = SteamAPIScrap.getMemberData(steamID);
            steamAPIGameData = SteamAPIScrap.getGameData(steamID);
        } catch (IOException | ParseException | APIConnectionException e) {
            e.printStackTrace();
        }

        Member savedMember = memberRepository.save(Member.builder()
                .ssgameId(requestMemberDto.getSsgameId())
                .password(encryptedPassword)
                .email(requestMemberDto.getEmail())
                .steamID(steamID)
                .steamNickname((String) steamAPIMemberData.get("steamNickname"))
                .avatarUrl((String) steamAPIMemberData.get("avatarUrl"))
                .isPublic((boolean) steamAPIMemberData.get("isPublic"))
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
        for (Long steamAppid: memberGameList.keySet()) {
            Game game = gameRepository.findBySteamAppid(steamAppid);
            // steam app id에 해당하는 게임 저장
            if (game == null) {
                // 게임 정보를 db에 저장한 이후에 사용
//                throw new GameNotFoundException(steamAppid);
                game = gameRepository.save(Game.builder()
                        .gameName("beta test")
                        .steamAppid(steamAppid)
                        .isFree(true)
                        .build());
            }
            // 회원가입 하고 나서 수정할 때는... 또 다른 로직이 필요함...
            // 새로 추가한 게임이나 기존 게임에서 플레이 시간만 업데이트 하는 로직
            memberGameListRepository.save(MemberGameList.builder()
                    .member(savedMember)
                    .game(game)
                    .memberPlayTime(memberGameList.get(steamAppid))
                    .build());
        }
    }

    @Override
    public ResponseLoginMemberDto findResponseLoginMemberDtoByMemberSeq(Long memberSeq) {

        Member member = memberRepository.findByMemberSeq(memberSeq);
        if (member == null) {
            throw new EntityNotFoundException("cannot find member by " + memberSeq);
        }

        List<MemberPreferredCategory> categories = memberPreferredCategoryRepository.findAllByMember(member);

        List<String> preferredCategories = new ArrayList<>();
        for (MemberPreferredCategory category : categories) {
            preferredCategories.add(category.getCategory().getCategoryName());
        }

        return ResponseLoginMemberDto.builder()
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
    public Member findMemberBySsgameId(String ssgameId) {
        return memberRepository.findBySsgameId(ssgameId);
    }

    @Override
    public ResponseLoginMemberDto findResponseLoginMemberDtoBySsgameId(String ssgameId) {

        Member member = memberRepository.findBySsgameId(ssgameId);
        if (member == null) {
            throw new EntityNotFoundException("cannot find member by " + ssgameId);
        }

        List<MemberPreferredCategory> categories = memberPreferredCategoryRepository.findAllByMember(member);

        List<String> preferredCategories = new ArrayList<>();
        for (MemberPreferredCategory category : categories) {
            preferredCategories.add(category.getCategory().getCategoryName());
        }

        return ResponseLoginMemberDto.builder()
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

}
