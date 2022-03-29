package com.sixteam.ssgame.api.gameInfo.service;

import com.sixteam.ssgame.api.gameInfo.dto.response.ResponseGameInfoDto;
import com.sixteam.ssgame.api.gameInfo.entity.GameGenre;
import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import com.sixteam.ssgame.api.gameInfo.entity.MemberGameList;
import com.sixteam.ssgame.api.gameInfo.repository.GameGenreRepository;
import com.sixteam.ssgame.api.gameInfo.repository.GameInfoRepository;
import com.sixteam.ssgame.api.gameInfo.repository.MemberGameListRepository;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
import com.sixteam.ssgame.global.common.util.LogUtil;
import com.sixteam.ssgame.global.error.dto.ErrorStatus;
import com.sixteam.ssgame.global.error.exception.CustomException;

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
    public ResponseGameInfoDto findResponseGameInfoDto(Long gameSeq, Long memberSeq) {

        GameInfo gameInfo = gameInfoRepository.findByGameSeq(gameSeq);
        if (gameInfo == null) {
            throw new CustomException("game not found by " + gameSeq, ErrorStatus.GAME_NOT_FOUND);
        }

        // movie json parsing -> mp4 480
        String movieUrl = null;
        String movieString = gameInfo.getMovies();
        if (movieString != null) {
            movieString = movieString.replaceAll("'", "\"").replaceAll("True", "true").replaceAll("False", "false");
            // JSONParser로 JSONObject로 변환
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = null;
            try {
                jsonArray = (JSONArray) parser.parse(movieString);
                // 왠진 모르겠지만 다 날라가고 mp4부터 남는다. 나중에 오류 발생 여지 있음
                JSONObject movieJson = (JSONObject) jsonArray.get(0);
                movieUrl = (String) ((JSONObject) movieJson.get("mp4")).get("480");
            } catch (ParseException e) {
                log.error("Parse Exception: {}", LogUtil.getClassAndMethodName());
            }
        }

        // average rating 구하는 query dsl
        Double averageRating = memberGameListRepository.getAverageRatingByGameSeq(gameSeq).getAverageRating();

        // 회원 관련 속성 - isPlayed, isRated, memberGameRating
        MemberGameList memberGameList = memberGameListRepository.findByMemberAndGameInfo(memberRepository.findByMemberSeq(memberSeq), gameInfo);
        boolean isMemberPlayedGame = (memberGameList != null);
        boolean isMemberRated = (memberGameList.getMemberGameRating() != null);

        // genre
        List<GameGenre> gameGenres = gameGenreRepository.findAllByGameInfo(gameInfo);
        List<String> genreNames = new ArrayList<>();
        if (gameGenres != null) {
            for (GameGenre gameGenre : gameGenres) {
                genreNames.add(gameGenre.getGenre().getGenreNameKr());
                if (genreNames.size() == 3) break;
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
                .isRated(isMemberPlayedGame && isMemberRated)
                .memberGameRating((isMemberPlayedGame && isMemberRated) ? memberGameList.getMemberGameRating() : -1)
                .genres(genreNames)
                .build();
    }
}
