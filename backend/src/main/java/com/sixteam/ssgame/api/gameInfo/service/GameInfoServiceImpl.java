package com.sixteam.ssgame.api.gameInfo.service;

import com.sixteam.ssgame.api.gameInfo.dto.ReleaseDateDto;
import com.sixteam.ssgame.api.gameInfo.dto.response.ResponseGameInfoDto;
import com.sixteam.ssgame.api.gameInfo.entity.GameGenre;
import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import com.sixteam.ssgame.api.gameInfo.entity.MemberGameList;
import com.sixteam.ssgame.api.gameInfo.repository.GameGenreRepository;
import com.sixteam.ssgame.api.gameInfo.repository.GameInfoRepository;
import com.sixteam.ssgame.api.gameInfo.repository.MemberGameListRepository;
import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;
import com.sixteam.ssgame.global.common.util.LogUtil;

import com.sixteam.ssgame.global.error.exception.CustomException;
import com.sixteam.ssgame.global.error.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import static com.sixteam.ssgame.global.error.dto.ErrorStatus.*;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class GameInfoServiceImpl implements GameInfoService {

    private final GameInfoRepository gameInfoRepository;

    private final MemberGameListRepository memberGameListRepository;

    private final GameGenreRepository gameGenreRepository;

    private final MemberRepository memberRepository;

    @Override
    public ResponseGameInfoDto findResponseGameInfoDto(Long gameSeq, CustomUserDetails details) {

        GameInfo gameInfo = gameInfoRepository.findByGameSeq(gameSeq)
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), GAME_NOT_FOUND));
        Member member = memberRepository.findBySsgameId(details.getUsername())
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), MEMBER_NOT_FOUND));

        // movie json parsing 함수 호출
        String movieUrl = jsonParsingMovies(gameInfo);

        // average rating 구하는 query dsl
        Double averageRating = memberGameListRepository.getAverageRatingByGameSeq(gameSeq).getAverageRating();

        // 회원 관련 속성 - isPlayed, isRated, memberGameRating
        Optional<MemberGameList> memberGameList = memberGameListRepository.findByMemberAndGameInfo(member, gameInfo);

        boolean isMemberPlayedGame = memberGameList.isPresent();
        boolean isMemberRated = isMemberPlayedGame && (memberGameList.get().getMemberGameRating() != 0);

        // genre
        List<GameGenre> gameGenres = gameGenreRepository.findAllByGameInfo(gameInfo);
        List<String> genreNames = new ArrayList<>();
        if (gameGenres != null) {
            for (GameGenre gameGenre : gameGenres) {
                genreNames.add(gameGenre.getGenre().getGenreNameKr());
            }
        }

        return ResponseGameInfoDto.builder()
                .gameSeq(gameInfo.getGameSeq())
                .gameName(gameInfo.getGameName())
                .shortDescriptionKr(gameInfo.getShortDescriptionKr())
                .headerImage(gameInfo.getHeaderImage())
                .movies(movieUrl)
                .averageRating(averageRating)
                .isPlayed(isMemberPlayedGame)
                .isRated(isMemberRated)
                .memberGameRating(isMemberRated ? memberGameList.get().getMemberGameRating() : 0)
                .genres(genreNames)
                .averageForever(gameInfo.getAverageForever())
                .platforms(jsonParsingPlatforms(gameInfo.getPlatforms()))
                .website(gameInfo.getWebsite().equals("0") ? "" : gameInfo.getWebsite())
                .languages(stringToList(gameInfo.getLanguages()))
                .publisher(stringToList(gameInfo.getPublisher()))
                .developers(stringToList(gameInfo.getDevelopers()))
                .releaseDate(jsonParsingReleaseDate(gameInfo.getReleaseDate()))
                .build();
    }

    @Override
    public String jsonParsingMovies(GameInfo gameInfo) {
        // movie json parsing -> mp4 480
        String movieUrl = null;
        String movieString = gameInfo.getMovies();

        if (movieString != null && movieString.length() > 1) {
            movieString = movieString.replaceAll("'", "\"").replaceAll("True", "true").replaceAll("False", "false");

            // JSONParser로 JSONObject로 변환
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = null;

            try {
                jsonArray = (JSONArray) parser.parse(movieString);
                JSONObject movieJson = (JSONObject) jsonArray.get(0);
                movieUrl = (String) ((JSONObject) movieJson.get("mp4")).get("480");
            } catch (ParseException e) {
                log.error("Parse Exception: {}", LogUtil.getElement());
            }
        }
        return movieUrl;
    }

    private List<String> jsonParsingPlatforms(String jsonString) {

        List<String> platforms = new ArrayList<>();

        if (jsonString != null && jsonString.length() > 1) {
            jsonString = jsonString.replaceAll("'", "\"").replaceAll("True", "true").replaceAll("False", "false");
            // JSONParser로 JSONObject로 변환
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = null;

            try {
                JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
                if ((boolean) jsonObject.get("windows")) {
                    platforms.add("windows");
                }
                if ((boolean) jsonObject.get("mac")) {
                    platforms.add("mac");
                }
                if ((boolean) jsonObject.get("linux")) {
                    platforms.add("linux");
                }
            } catch (ParseException e) {
                log.error("Parse Exception: {}", LogUtil.getElement());
            }
        }

        return platforms;
    }

    private List<String> stringToList(String stringData) {

        List<String> listData = new ArrayList<>();

        if (stringData != null && stringData.length() > 1) {
            StringTokenizer st = new StringTokenizer(stringData, ",");

            while (st.hasMoreTokens()) {
                listData.add(st.nextToken().trim());
            }
        }

        return listData;
    }

    private ReleaseDateDto jsonParsingReleaseDate(String jsonString) {

        boolean comingSoon = false;
        String date = null;

        if (jsonString != null && jsonString.length() > 1) {
            jsonString = jsonString.replaceAll("'", "\"").replaceAll("True", "true").replaceAll("False", "false");
            // JSONParser로 JSONObject로 변환
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = null;

            try {
                JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
                comingSoon = (boolean) jsonObject.get("coming_soon");
                date = (String) jsonObject.get("date");
            } catch (ParseException e) {
                log.error("Parse Exception: {}", LogUtil.getElement());
            }
        }

        return ReleaseDateDto.builder()
                .comingSoon(comingSoon)
                .date(date == null ? "" : date)
                .build();
    }

    @Override
    @Transactional
    public void updateMemberGameRating(CustomUserDetails details, Long gameSeq, Integer memberGameRating) {

        if (memberGameRating < 1 || memberGameRating > 5) {
            throw new InvalidValueException(LogUtil.getElement(), INVALID_RANGE_OF_RATING);
        }

        Member member = memberRepository.findBySsgameId(details.getUsername())
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), MEMBER_NOT_FOUND));

        GameInfo gameInfo = gameInfoRepository.findByGameSeq(gameSeq)
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), GAME_NOT_FOUND));

        MemberGameList memberGameList = memberGameListRepository.findByMemberAndGameInfo(member, gameInfo)
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), ENTITY_NOT_FOUND));

        memberGameList.updateMemberGameRating(memberGameRating);
    }
}
