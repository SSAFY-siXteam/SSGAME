package com.sixteam.ssgame.api.game.service;

import com.sixteam.ssgame.api.game.dto.response.ResponseGameDto;
import com.sixteam.ssgame.api.game.entity.GameGenre;
import com.sixteam.ssgame.api.game.entity.Game;
import com.sixteam.ssgame.api.game.entity.MemberGameList;
import com.sixteam.ssgame.api.game.exception.GameNotFoundException;
import com.sixteam.ssgame.api.game.repository.GameGenreRepository;
import com.sixteam.ssgame.api.game.repository.GameRepository;
import com.sixteam.ssgame.api.game.repository.MemberGameListRepository;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
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
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    private final MemberGameListRepository memberGameListRepository;

    private final GameGenreRepository gameGenreRepository;

    private final MemberRepository memberRepository;

    @Override
    public ResponseGameDto findResponseGameDto(Long gameSeq, Long memberSeq) throws ParseException {

        Game game = gameRepository.findByGameSeq(gameSeq);
        if (game == null) {
            throw new GameNotFoundException(gameSeq);
        }

        // movie json parsing -> mp4 480
        String movieUrl = "no content";
        String movieString = game.getMovies();
        if (movieString != null) {
            movieString = movieString.replaceAll("'", "\"").replaceAll("True", "true").replaceAll("False", "false");
            // JSONParser로 JSONObject로 변환
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(movieString);
            // 왠진 모르겠지만 다 날라가고 mp4부터 남는다.. 나중에 오류 발생 여지 있음
            JSONObject movieJson = (JSONObject) jsonArray.get(0);
            movieUrl = (String) ((JSONObject) movieJson.get("mp4")).get("480");
        }

        // average rating 구하는 query....dsl....?

        // 회원 관련 속성 - isPlayed, isRated, memberGameRating
        MemberGameList memberGameList = memberGameListRepository.findByMemberAndGame(memberRepository.findByMemberSeq(memberSeq), game);
        boolean isMemberPlayedGame = (memberGameList != null);
        boolean isMemberRated = (memberGameList.getMemberGameRating() != null);

        // genre
        List<GameGenre> gameGenres = gameGenreRepository.findAllByGame(game);
        List<String> genreNames = new ArrayList<>();
        if (gameGenres != null) {
            for (GameGenre gameGenre : gameGenres) {
                genreNames.add(gameGenre.getGenre().getGenreNameKr());
                if (genreNames.size() == 3) break;
            }
        }

        return ResponseGameDto.builder()
                .gameSeq(game.getGameSeq())
                .gameName(game.getGameName())
                .shortDescriptionKr(game.getShortDescriptionKr())
                .headerImage(game.getHeaderImage())
                .movies(movieUrl)
                .averageRating(0D)
                .isPlayed(isMemberPlayedGame)
                .isRated(isMemberPlayedGame && isMemberRated)
                .memberGameRating((isMemberPlayedGame && isMemberRated) ? memberGameList.getMemberGameRating() : -1)
                .genres(genreNames)
                .build();
    }
}
