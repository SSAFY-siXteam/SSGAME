package com.sixteam.ssgame.api.recommendation.service;

import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import com.sixteam.ssgame.api.gameInfo.repository.MemberGameListRepository;
import com.sixteam.ssgame.api.gameInfo.service.GameInfoService;
import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
import com.sixteam.ssgame.api.recommendation.dto.ResponseMemberRecommendGameListDto;
import com.sixteam.ssgame.api.recommendation.dto.ResponseMemberRecommendedGameInfoDto;
import com.sixteam.ssgame.api.recommendation.entity.MemberRecommendedGame;
import com.sixteam.ssgame.api.recommendation.repository.MemberRecommendedGameRepository;
import com.sixteam.ssgame.global.error.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sixteam.ssgame.global.error.dto.ErrorStatus.LACK_OF_RECOMMENDED_GAME;

@Service
@Transactional(readOnly = true)
public class MemberRecommendedGameServiceImpl implements MemberRecommendedGameService {

    @Autowired
    private MemberRecommendedGameRepository recommendedGameRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberGameListRepository memberGameListRepository;

    @Autowired
    private GameInfoService gameInfoService;

    @Override
    public ResponseMemberRecommendGameListDto getRecommendedGameList(Long memberSeq) {
        Member member = memberRepository.findByMemberSeq(memberSeq);
        // Todo : member exception

        List<ResponseMemberRecommendedGameInfoDto> responseMemberRecommendedGameInfoDtos = new ArrayList<>();

        int rank = 1;
        for (MemberRecommendedGame memberRecommendedGame : member.getMemberRecommendedGames()) {
            // 게임 조회
            GameInfo gameInfo = memberRecommendedGame.getGameInfo();
            // Todo : gameInfo exception

            // 각 게임 별점 평균 연산
            Double averageRating = memberGameListRepository.getAverageRatingByGameSeq(gameInfo.getGameSeq()).getAverageRating();

            // 추천 게임의 정보
            responseMemberRecommendedGameInfoDtos.add(ResponseMemberRecommendedGameInfoDto.builder()
                    .gameSeq(gameInfo.getGameSeq())
                    .gameName(gameInfo.getGameName())
                    .headerImg(gameInfo.getHeaderImage())
                    .genres(gameInfo.getGameGenres().stream()
                            .map(gameGenre -> {
                                return gameGenre.getGenre().getGenreName();
                            })
                            .collect(Collectors.toList()))
                    .averageRating(averageRating)
                    .price(gameInfo.getPrice())
                    .movies(gameInfoService.jsonParsingMovies(gameInfo))
                    .recommendRanking(rank++)
                    .build());
        }

        // 추천 게임 개수 체크
        if(responseMemberRecommendedGameInfoDtos.size() != 11) {
            throw new CustomException("lack of recommended game", LACK_OF_RECOMMENDED_GAME);
        }

        return ResponseMemberRecommendGameListDto.builder()
                .responseMemberRecommendedGameInfoDtos(responseMemberRecommendedGameInfoDtos)
                .build();
    }
}
