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
import com.sixteam.ssgame.api.member.dto.request.RequestMemberDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseMemberDto;
import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.entity.MemberPreferredCategory;
import com.sixteam.ssgame.api.member.repository.MemberPreferredCategoryRepository;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
import com.sixteam.ssgame.global.common.steamapi.SteamAPIScrap;
import com.sixteam.ssgame.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.*;

import static com.sixteam.ssgame.global.error.dto.ErrorStatus.SSGAMEID_NOT_FOUND;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final MemberPreferredCategoryRepository memberPreferredCategoryRepository;

    private final MemberPreferredTagRepository memberPreferredTagRepository;

    private final CategoryRepository categoryRepository;

    private final MemberGameListRepository memberGameListRepository;

    private final GameInfoRepository gameInfoRepository;

    private final TagRepository tagRepository;

    private final GameGenreRepository gameGenreRepository;

    private final GenreRepository genreRepository;

    private final RadarChartInfoRepository radarChartInfoRepository;

    private final GameTagRepository gameTagRepository;

    private final MemberFrequentGenreRepository memberFrequentGenreRepository;

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
    public void register(RequestMemberDto requestMemberDto) throws IOException, ParseException {
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
        for (Long steamAppid : memberGameList.keySet()) {
            GameInfo gameInfo = gameInfoRepository.findBySteamAppid(steamAppid);
            // steam app id에 해당하는 게임 저장
            if (gameInfo == null) {
                continue;
                // 게임 정보를 db에 저장한 이후에 사용
//                throw new CustomException(steamAppid, GAME_NOT_FOUND);
//                gameInfo = gameInfoRepository.save(GameInfo.builder()
//                        .gameName("beta test")
//                        .steamAppid(steamAppid)
//                        .isFree(true)
//                        .build());
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

    @Override
    @Transactional(readOnly = false)
    public boolean loadGameInfoBySsgameId(String ssgameId) {

        Member member = memberRepository.findBySsgameId(ssgameId);
        if (member == null) {
            return false;
        }
        try {
            Map<String, Object> gameData = SteamAPIScrap.getGameData(member.getSteamID());
            Long gameCount = (long) gameData.get("gameCount");
//            memberGameListRepository.deleteByMember(member);
            //게임 갯수가 0개인 부분 필터링
            if (gameCount == 0) {
                System.out.println("No games");
            } else {
                List<Genre> genres = genreRepository.findAll();
                Map<Long, Integer> genresCount = new HashMap<>();

                for (Genre genre : genres) {
                    genresCount.put(genre.getGenreSeq(), 0);
                }
                //game list 파싱
                Map<Long, Long> memberGameList = (Map<Long, Long>) gameData.get("memberGameList");

                for (Long steamAppid : memberGameList.keySet()) {
                    GameInfo gameInfo = gameInfoRepository.findBySteamAppid(steamAppid);
                    //DB에 등록된 게임인지 확인
                    if (gameInfo != null) {
                        //이미 멤버 게임 리스트에 등록되어있는지 확인
                        MemberGameList alreadySaved = memberGameListRepository
                                .findByMemberAndGameInfo(member, gameInfo);

                        if (alreadySaved != null) {
                            //등록되어있다면 GameRating은 그대로 두고 playTime만 바꾸어 UPDATE
                            memberGameListRepository.save(MemberGameList.builder()
                                    .member(member)
                                    .gameInfo(gameInfo)
                                    .memberPlayTime(memberGameList.get(steamAppid))
                                    .memberGameRating(alreadySaved.getMemberGameRating())
                                    .build());
                        } else {
                            //등록되지 않은 게임이라면 GameRating을 0으로 하고 CREATE
                            memberGameListRepository.save(MemberGameList.builder()
                                    .member(member)
                                    .gameInfo(gameInfo)
                                    .memberPlayTime(memberGameList.get(steamAppid))
                                    .memberGameRating(0)
                                    .build());
                        }

                        if (memberGameList.get(steamAppid) == 0) continue;
                        List<GameGenre> gameGenres = gameGenreRepository.findAllByGameInfo(gameInfo);
                        for (GameGenre gameGenre : gameGenres) {
                            genresCount.put(gameGenre.getGenre().getGenreSeq(), genresCount.get(gameGenre.getGenre().getGenreSeq()) + 1);
                        }
                    }
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    @Override
    @Transactional(readOnly = false)
    public boolean calcMemberPrefferred(String ssgameId) {

        Member member = memberRepository.findBySsgameId(ssgameId);
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
            GameInfo gameInfo = gameInfoRepository.findByGameSeq(memberGame.getGameInfo().getGameSeq());
            List<GameTag> gameTags = gameTagRepository.getByGameInfo(gameInfo);

            //멤버가 가진 게임들의 태그마다의 Value를 tagsValue에 합산
            for (GameTag gameTag : gameTags) {

                //게임의 태그값 * 플레이시간가중치 * 별점가중치 * 카테고리가중치를 합산한뒤 소수점 6자리에서 반올림.
                double TimeWeight = memberGame.getMemberPlayTime() < (long) gameInfo.getAverageForever() ?
                        (double) memberGame.getMemberPlayTime() / (double) gameInfo.getAverageForever() : 1;
                double GameRatingWeight = memberGame.getMemberGameRating() == 3 ?
                        1 : (1 + ((memberGame.getMemberGameRating() - 3) * 0.2));

                tagsValue.put(gameTag.getTag().getTagSeq(), tagsValue.get(gameTag.getTag().getTagSeq())
                        + Math.round(gameTag.getTagRatio() * TimeWeight * GameRatingWeight
                        * categoryWeight[tagsCategory.get(gameTag.getTag().getTagSeq())] * 100000.0) / 100000.0);

                if (memberGame.getMemberPlayTime() != 0) {
                    categoryMax[tagsCategory.get(gameTag.getTag().getTagSeq())] += 5;
                    categoryValue[tagsCategory.get(gameTag.getTag().getTagSeq())] +=
                            memberGame.getMemberGameRating() != 0 ? memberGame.getMemberGameRating() : 3;
                }
            }
        }

        //모든 value들의 총합을 구한뒤 각 value들을 valueSum으로 나눠 퍼센트를 구함.
        double valueSum = 0.0;
        System.out.println("~~~~" + tagsValue);

        for (Tag tag : tags) {
            valueSum += tagsValue.get(tag.getTagSeq());
        }
        System.out.println("&*(" + valueSum);

        for (Tag tag : tags) {
            tagsValue.put(tag.getTagSeq(), Math.round(tagsValue.get(tag.getTagSeq()) / valueSum * 100000.0) / 100000.0);
        }

        //멤버 선호 태그 테이블에 save
        for (Tag tag : tags) {
            memberPreferredTagRepository.save(MemberPreferredTag.builder()
                    .member(member)
                    .tag(tag)
                    .preferredTagRatio(tagsValue.get(tag.getTagSeq()))
                    .build());
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
                            / (double) categoryMax[category.getCategorySeq().intValue()])
                    .build());
        }

        return true;
    }
}
