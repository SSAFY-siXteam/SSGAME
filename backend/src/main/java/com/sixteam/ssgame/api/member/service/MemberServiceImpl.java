package com.sixteam.ssgame.api.member.service;

import com.sixteam.ssgame.api.analysis.entity.Category;
import com.sixteam.ssgame.api.analysis.entity.MemberFrequentGenre;
import com.sixteam.ssgame.api.analysis.entity.RadarChartInfo;
import com.sixteam.ssgame.api.analysis.repository.CategoryRepository;
import com.sixteam.ssgame.api.analysis.repository.MemberFrequentGenreRepository;
import com.sixteam.ssgame.api.analysis.repository.RadarChartInfoRepository;
import com.sixteam.ssgame.api.gameInfo.entity.*;
import com.sixteam.ssgame.api.gameInfo.repository.*;
import com.sixteam.ssgame.api.member.dto.MemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestLoginMemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestMemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestUpdateMemberDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseMemberDto;
import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.entity.MemberPreferredCategory;
import com.sixteam.ssgame.api.member.repository.MemberPreferredCategoryRepository;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
import com.sixteam.ssgame.api.recommendation.repository.MemberRecommendedGameRepository;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;
import com.sixteam.ssgame.global.common.steamapi.SteamAPIScrap;
import com.sixteam.ssgame.global.common.util.LogUtil;
import com.sixteam.ssgame.global.error.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import static com.sixteam.ssgame.global.error.dto.ErrorStatus.*;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final MemberRecommendedGameRepository memberRecommendedGameRepository;

    private final MemberPreferredCategoryRepository memberPreferredCategoryRepository;

    private final MemberPreferredTagRepository memberPreferredTagRepository;

    private final CategoryRepository categoryRepository;

    private final MemberGameListRepository memberGameListRepository;

    private final GameInfoRepository gameInfoRepository;

    private final MemberFrequentGenreRepository memberFrequentGenreRepository;

    private final RadarChartInfoRepository radarChartInfoRepository;

    private final TagRepository tagRepository;

    private final GameGenreRepository gameGenreRepository;

    private final GenreRepository genreRepository;

    private final GameTagRepository gameTagRepository;

    private final PasswordEncoder passwordEncoder;

    private final EntityManager em;

    @Override
    public boolean hasSsgameId(String ssgameId) {

        String regx = "^[a-z]+[0-9a-z]{3,15}$";
        Pattern pattern = Pattern.compile(regx);

        if (!pattern.matcher(ssgameId).matches()) {
            throw new CustomException(LogUtil.getElement(), INVALID_ID_FORMAT);
        }

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
    public boolean register(RequestMemberDto requestMemberDto) {

        String ssgameId = requestMemberDto.getSsgameId();
        String steamID = requestMemberDto.getSteamID();
        // validation check
        if (hasSsgameId(ssgameId)) {
            throw new CustomException(LogUtil.getElement(), SSGAMEID_DUPLICATION);
        } else if (requestMemberDto.getPassword().contains(ssgameId)) {
            throw new CustomException(LogUtil.getElement(), PASSWORD_CONTAINED_SSGAMEID);
        } else if (hasSteamID(steamID)) {
            throw new CustomException(LogUtil.getElement(), STEAMID_DUPLICATION);
        } else if (hasEmail(requestMemberDto.getEmail())) {
            throw new CustomException(LogUtil.getElement(), EMAIL_DUPLICATION);
        }

        String encryptedPassword = passwordEncoder.encode(requestMemberDto.getPassword());
        log.debug("패스워드 암호화 " + encryptedPassword);

        try {
            Map<String, Object> steamAPIMemberData = SteamAPIScrap.getMemberData(steamID);
            Map<String, Object> steamAPIGameData = SteamAPIScrap.getGameData(steamID);

            Member savedMember = memberRepository.save(Member.builder()
                    .ssgameId(ssgameId)
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
                for (Long steamAppid : memberGameList.keySet()) {
                    Optional<GameInfo> gameInfo = gameInfoRepository.findBySteamAppid(steamAppid);

                    // steam app id에 해당하는 게임 저장
                    gameInfo.ifPresent(f -> {
                        memberGameListRepository.save(MemberGameList.builder()
                                .member(savedMember)
                                .gameInfo(gameInfo.get())
                                .memberPlayTime(memberGameList.get(steamAppid))
                                .memberGameRating(0)
                                .build());
                    });
                }
            }

            return savedMember.getIsPublic();
        } catch (ParseException e) {
            throw new CustomException(LogUtil.getElement(), JSON_PARSE_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(LogUtil.getElement(), FAIL_TO_REGISTER);
        }
    }

    @Override
    public MemberDto findMemberDtoInLogin(RequestLoginMemberDto requestLoginMemberDto) {

        Member member = memberRepository.findBySsgameId(requestLoginMemberDto.getSsgameId())
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), SSGAMEID_NOT_FOUND));

        if (!passwordEncoder.matches(requestLoginMemberDto.getPassword(), member.getPassword())) {
            throw new CustomException(LogUtil.getElement(), PASSWORD_NOT_MATCH);
        }

        return findMemberDtoByMember(member);
    }

    @Override
    public ResponseMemberDto findResponseMemberDto(CustomUserDetails details) {

        String ssgameId = details.getUsername();
        Member member = memberRepository.findBySsgameId(ssgameId)
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), MEMBER_NOT_FOUND));
        MemberDto memberDto = findMemberDtoByMember(member);

        return ResponseMemberDto.builder()
                .ssgameId(memberDto.getSsgameId())
                .email(memberDto.getEmail())
                .steamID(memberDto.getSteamID())
                .steamNickname(memberDto.getSteamNickname())
                .avatarUrl(memberDto.getAvatarUrl())
                .gameCount(memberDto.getGameCount())
                .preferredCategories(memberDto.getPreferredCategories())
                .build();
    }

    private MemberDto findMemberDtoByMember(Member member) {

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
    public Member findMemberBySsgameId(String ssgameId) {
        return memberRepository.findBySsgameId(ssgameId)
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), MEMBER_NOT_FOUND));
    }

    @Transactional
    @Override
    public boolean renewalMemberData(CustomUserDetails details) {

        String ssgameId = details.getUsername();
        Member member = memberRepository.findBySsgameId(ssgameId).orElse(null);
        if (member == null) {
            return false;
        }

        // steamID로 api 호출
        Map<String, Object> steamGameData = loadGameInfoByMember(member);
        if (!(boolean) steamGameData.get("isSuccess")) {
            return false;
        }
        // 게임 정보 최신화 이후에 가중치 업데이트
        if (!calcMemberPrefferred(member)) {
            return false;
        }

        try {
            Map<String, Object> steamMemberData = SteamAPIScrap.getMemberData(member.getSteamID());
            member.changeMemberSteamAPI((String) steamMemberData.get("steamNickname"), (String) steamMemberData.get("avatarUrl"), true, (Long) steamGameData.get("gameCount"));
        } catch (IOException | ParseException e) {
            log.debug("IOException | ParseException in API: {}", LogUtil.getElement());
            return false;
        }

        return true;
    }

    @Transactional(readOnly = false)
    public Map<String, Object> loadGameInfoByMember(Member member) {

        Map<String, Object> responseData = new HashMap<>();

        try {
            Map<String, Object> gameData = SteamAPIScrap.getGameData(member.getSteamID());
            Long gameCount = (long) gameData.get("gameCount");
            responseData.put("gameCount", gameCount);
//            memberGameListRepository.deleteByMember(member);
            //게임 갯수가 0개인 부분 필터링
            if (gameCount != 0) {
                List<Genre> genres = genreRepository.findAll();
                Map<Long, Integer> genresCount = new HashMap<>();

                for (Genre genre : genres) {
                    genresCount.put(genre.getGenreSeq(), 0);
                }
                //game list 파싱
                Map<Long, Long> memberGameList = (Map<Long, Long>) gameData.get("memberGameList");

                for (Long steamAppid : memberGameList.keySet()) {
                    Optional<GameInfo> gameInfoOptional = gameInfoRepository.findBySteamAppid(steamAppid);
                    //DB에 등록된 게임인지 확인
                    gameInfoOptional.ifPresent(f1 -> {
                        GameInfo gameInfo = gameInfoOptional.get();
                        //이미 멤버 게임 리스트에 등록되어있는지 확인
                        Optional<MemberGameList> alreadySaved = memberGameListRepository
                                .findByMemberAndGameInfo(member, gameInfo);

                        alreadySaved.ifPresentOrElse(f2 -> {
                            memberGameListRepository.save(MemberGameList.builder()
                                    .memberGameListSeq(alreadySaved.get().getMemberGameListSeq())
                                    .member(member)
                                    .gameInfo(gameInfo)
                                    .memberPlayTime(memberGameList.get(steamAppid))
                                    .memberGameRating(alreadySaved.get().getMemberGameRating())
                                    .build());
                        }, () -> {
                            //등록되지 않은 게임이라면 GameRating을 0으로 하고 CREATE
                            memberGameListRepository.save(MemberGameList.builder()
                                    .member(member)
                                    .gameInfo(gameInfo)
                                    .memberPlayTime(memberGameList.get(steamAppid))
                                    .memberGameRating(0)
                                    .build());
                        });

                        if (memberGameList.get(steamAppid) != 0) {
                            List<GameGenre> gameGenres = gameGenreRepository.findAllByGameInfo(gameInfo);
                            for (GameGenre gameGenre : gameGenres) {
                                genresCount.put(gameGenre.getGenre().getGenreSeq(), genresCount.get(gameGenre.getGenre().getGenreSeq()) + 1);
                            }
                        }
                    });
                }

                memberFrequentGenreRepository.deleteByMember(member);
                List<Map.Entry<Long, Integer>> entryList = new LinkedList<>(genresCount.entrySet());
                entryList.sort(Map.Entry.comparingByValue());
                for (int i = 0; i < entryList.size(); i++) {
                    memberFrequentGenreRepository.save(MemberFrequentGenre.builder()
                            .member(member)
                            .genre(genres.get(entryList.get(i).getKey().intValue() - 1))
                            .genreCount((long) entryList.get(i).getValue())
                            .build());
                }
            }
        } catch (ParseException | IOException e) {
            log.debug("IOException | ParseException in API: {}", LogUtil.getElement());
            responseData.put("isSuccess", false);
        }

        responseData.put("isSuccess", true);
        return responseData;
    }

    @Transactional(readOnly = false)
    public boolean calcMemberPrefferred(Member member) {

        List<Tag> tags = tagRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        //카테고리 퍼센트를 구하기 위한 배열들
        int[] categoryValue = new int[categories.size() + 1];
        int[] categoryMax = new int[categories.size() + 1];
        //0으로 초기화
        Arrays.fill(categoryValue, 0);
        Arrays.fill(categoryMax, 0);

        //tag 들의 Value를 더하기 위한 Map
        HashMap<Long, Double> tagsValue = new HashMap<>();
        //태그들의 카테고리를 저장하기 위한 Map
        HashMap<Long, Integer> tagsCategory = new HashMap<>();

        for (Tag tag : tags) {
            // 태그마다 합을 구하기 위해 0으로 초기화
            tagsValue.put(tag.getTagSeq(), 0.0);
            // 태그별 카테고리 Seq 값을 저장
            tagsCategory.put(tag.getTagSeq(), tag.getCategory().getCategorySeq().intValue());
        }

        //카테고리 가중치를 계산
        double[] categoryWeight = new double[categories.size() + 1];
        Arrays.fill(categoryWeight, 1.0);
        List<MemberPreferredCategory> memberPreferredCategories = memberPreferredCategoryRepository.findAllByMember(member);
        for (MemberPreferredCategory memberPreferredCategory : memberPreferredCategories) {
            categoryWeight[memberPreferredCategory.getCategory().getCategorySeq().intValue()] = 1.2;
        }

        //이미 등록된 멤버태그내용 삭제 후 진행
        memberPreferredTagRepository.deleteByMember(member);

        //멤버의 게임 리스트 가져오기
        List<MemberGameList> memberGameLists = memberGameListRepository.findByMember(member);

        for (MemberGameList memberGame : memberGameLists) {
            if (memberGame.getMemberPlayTime() == 0) {
                continue;
            }
            GameInfo gameInfo = gameInfoRepository.findByGameSeq(memberGame.getGameInfo().getGameSeq())
                    .orElseThrow(() -> new CustomException(memberGame.getGameInfo().getGameSeq().toString(), GAME_NOT_FOUND));
            List<GameTag> gameTags = gameTagRepository.getByGameInfo(gameInfo);

            //멤버가 가진 게임들의 태그마다의 Value를 tagsValue에 합산
            for (GameTag gameTag : gameTags) {

                //게임의 태그값 * 플레이시간가중치 * 별점가중치 * 카테고리가중치를 합산한뒤 소수점 6자리에서 반올림.
                double TimeWeight = memberGame.getMemberPlayTime() < (long) gameInfo.getAverageForever() ?
                        (double) memberGame.getMemberPlayTime() / (double) gameInfo.getAverageForever() : 1;
                double GameRatingWeight = memberGame.getMemberGameRating() == 3 || memberGame.getMemberGameRating() == 0 ?
                        1 : (1 + ((memberGame.getMemberGameRating() - 3) * 0.2));

                tagsValue.put(gameTag.getTag().getTagSeq(), tagsValue.get(gameTag.getTag().getTagSeq())
                        + Math.round(gameTag.getTagRatio() * TimeWeight * GameRatingWeight
                        * categoryWeight[tagsCategory.get(gameTag.getTag().getTagSeq())] * 100000.0) / 100000.0);

                if (memberGame.getMemberPlayTime() != 0) {
                    categoryMax[tagsCategory.get(gameTag.getTag().getTagSeq())] += 5;
                    categoryValue[tagsCategory.get(gameTag.getTag().getTagSeq())] +=
                            memberGame.getMemberGameRating() != 0 ? memberGame.getMemberGameRating() : 2;
                }
            }
        }

        //모든 value들의 총합을 구한뒤 각 value들을 valueSum으로 나눠 퍼센트를 구함.
        double valueSum = 0.0;

        for (Tag tag : tags) {
            valueSum += tagsValue.get(tag.getTagSeq());
        }

        for (Tag tag : tags) {
            tagsValue.put(tag.getTagSeq(), Math.round(tagsValue.get(tag.getTagSeq()) / valueSum * 100000.0) / 100000.0);
        }

        //멤버 선호 태그 테이블에 save
        for (Tag tag : tags) {
            MemberPreferredTag memberPreferredTag = memberPreferredTagRepository.findByMemberAndTag(member,tag);
            if(memberPreferredTag==null){
                memberPreferredTagRepository.save(MemberPreferredTag.builder()
                        .member(member)
                        .tag(tag)
                        .preferredTagRatio(tagsValue.get(tag.getTagSeq()))
                        .build());
            }else {
                memberPreferredTagRepository.save(MemberPreferredTag.builder()
                        .memberTagSeq(memberPreferredTag.getMemberTagSeq())
                        .member(member)
                        .tag(tag)
                        .preferredTagRatio(tagsValue.get(tag.getTagSeq()))
                        .build());
            }
        }

        radarChartInfoRepository.deleteByMember(member);
        for (Category category : categories) {
            if (categoryMax[category.getCategorySeq().intValue()] == 0) {
                radarChartInfoRepository.save(RadarChartInfo.builder()
                        .member(member)
                        .category(category)
                        .categoryRatio(0.0)
                        .build());
                continue;
            }
            radarChartInfoRepository.save(RadarChartInfo.builder()
                    .member(member)
                    .category(category)
                    .categoryRatio((double) categoryValue[category.getCategorySeq().intValue()]
                            / (double) categoryMax[category.getCategorySeq().intValue()] * 100)
                    .build());
        }

        return true;
    }

    @Transactional
    @Override
    public void updateMember(CustomUserDetails details, RequestUpdateMemberDto requestUpdateMemberDto) {

        String ssgameId = details.getUsername();

        Member member = memberRepository.findBySsgameId(ssgameId)
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), MEMBER_NOT_FOUND));

        String password = member.getPassword();
        if (!passwordEncoder.matches(requestUpdateMemberDto.getPrePassword(), password)) {
            throw new CustomException(LogUtil.getElement(), PASSWORD_NOT_MATCH);
        }
        String newPassword = requestUpdateMemberDto.getNewPassword();
        if (newPassword != null) {
            password = passwordEncoder.encode(newPassword);
        }

        String email = requestUpdateMemberDto.getEmail();
        if (!member.getEmail().equals(email) && hasEmail(email)) {
            throw new CustomException(LogUtil.getElement(), EMAIL_DUPLICATION);
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
            calcMemberPrefferred(member);
        }
    }

    @Transactional
    @Override
    public void updateMemberSteamID(CustomUserDetails details, String steamID) {

        String ssgameId = details.getUsername();

        Member member = memberRepository.findBySsgameId(ssgameId)
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), SSGAMEID_NOT_FOUND));

        if (member.getSteamID().equals(steamID)) {
            throw new CustomException(LogUtil.getElement(), SAME_STEAM_ID);
        }

        if (!member.getSteamID().equals(steamID) && hasSteamID(steamID)) {
            throw new CustomException(LogUtil.getElement(), STEAMID_DUPLICATION);
        }

        try {
            // 새로운 steamID.isPublic == false면 에러 반환
            Map<String, Object> steamGameData = SteamAPIScrap.getGameData(steamID);
            if(!(boolean) steamGameData.get("isPublic")) {
                throw new CustomException(LogUtil.getElement(), PRIVATE_STEAMID);
            }

            /**
             * 변경되는 테이블
             * 1. 사용자 게임 -> loadGameInfoBySsgameId
             * 2. 사용자 추천 게임 -> 장고에서 진행 (프론트 요청사항)
             * 3. 사용자 선호 태그 -> calcMemberPrefferred
             * 4. 사용자가 가장 많이 플레이한 장르 -> loadGameInfoBySsgameId
             * 5. 분석그래프 정보 -> calcMemberPrefferred
             */

            // 기존 steamID에 관련된 내용 삭제
            memberGameListRepository.deleteByMember(member);

            // steamID 변경
            Map<String, Object> steamMemberData = SteamAPIScrap.getMemberData(steamID);
            member.changeMemberSteamID(steamID, (String) steamMemberData.get("steamNickname"), (String) steamMemberData.get("avatarUrl"), true, (Long) steamGameData.get("gameCount"));

            // 새로운 steamID로 재등록
            loadGameInfoByMember(member);
            calcMemberPrefferred(member);
        } catch (ParseException | IOException e) {
            throw new CustomException(LogUtil.getElement(), JSON_PARSE_ERROR);
        }
    }



    @Override
    @Transactional
    public void deleteMember(String ssgameId) {

        Member member = memberRepository.findBySsgameId(ssgameId)
                .orElseThrow(() -> new CustomException("cannot find member", MEMBER_NOT_FOUND));

        radarChartInfoRepository.deleteByMember(member);
        memberPreferredCategoryRepository.deleteAllByMember(member);
        memberFrequentGenreRepository.deleteByMember(member);
        memberPreferredTagRepository.deleteByMember(member);
        memberGameListRepository.deleteByMember(member);
        memberRecommendedGameRepository.deleteByMember(member);
        memberGameListRepository.deleteByMember(member);
        memberRepository.deleteById(member.getMemberSeq());
    }
}
