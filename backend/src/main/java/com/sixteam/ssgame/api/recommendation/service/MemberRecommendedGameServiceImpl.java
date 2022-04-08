package com.sixteam.ssgame.api.recommendation.service;

import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import com.sixteam.ssgame.api.gameInfo.repository.MemberGameListRepository;
import com.sixteam.ssgame.api.gameInfo.service.GameInfoService;
import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
import com.sixteam.ssgame.api.recommendation.dto.ResponseMemberRecommendedGameInfoDto;
import com.sixteam.ssgame.api.recommendation.entity.MemberRecommendedGame;
import com.sixteam.ssgame.global.common.util.LogUtil;
import com.sixteam.ssgame.global.error.dto.ErrorStatus;
import com.sixteam.ssgame.global.error.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sixteam.ssgame.global.error.dto.ErrorStatus.*;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberRecommendedGameServiceImpl implements MemberRecommendedGameService {

    private final MemberRepository memberRepository;

    private final MemberGameListRepository memberGameListRepository;

    private final GameInfoService gameInfoService;

    @Override
    public List<ResponseMemberRecommendedGameInfoDto> getRecommendedGameList(Long memberSeq) {

        Member member = memberRepository.findByMemberSeq(memberSeq)
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), MEMBER_NOT_FOUND));

        if (member.getGameCount() == 0) {
            throw new CustomException(LogUtil.getElement(), NO_GAME_PLAYED);
        }

        List<ResponseMemberRecommendedGameInfoDto> responseMemberRecommendedGameInfoDtos = new ArrayList<>();

        int rank = 1;
        for (MemberRecommendedGame memberRecommendedGame : member.getMemberRecommendedGames()) {
            // 게임 조회
            GameInfo gameInfo = memberRecommendedGame.getGameInfo();
            if (gameInfo == null){
                throw new CustomException(LogUtil.getElement(), ErrorStatus.GAME_NOT_FOUND);
            }

            // 각 게임 별점 평균 연산
            Double averageRating = memberGameListRepository.getAverageRatingByGameSeq(gameInfo.getGameSeq()).getAverageRating();

            // 추천 게임의 정보
            responseMemberRecommendedGameInfoDtos.add(ResponseMemberRecommendedGameInfoDto.builder()
                    .gameSeq(gameInfo.getGameSeq())
                    .gameName(gameInfo.getGameName())
                    .headerImage(gameInfo.getHeaderImage())
                    .genres(gameInfo.getGameGenres().stream()
                            .map(gameGenre -> {
                                return gameGenre.getGenre().getGenreNameKr();
                            })
                            .collect(Collectors.toList()))
                    .averageRating(averageRating)
                    .price(gameInfo.getPrice())
                    .movies(gameInfoService.jsonParsingMovies(gameInfo))
                    .recommendRanking(rank++)
                    .build());
        }

        // 추천 게임 개수 체크
        if(responseMemberRecommendedGameInfoDtos.size() < 11) {
            throw new CustomException(LogUtil.getElement(), LACK_OF_RECOMMENDED_GAME);
        }

        return responseMemberRecommendedGameInfoDtos;
    }
}
