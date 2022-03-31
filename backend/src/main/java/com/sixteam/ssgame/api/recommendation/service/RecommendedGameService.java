package com.sixteam.ssgame.api.recommendation.service;


import com.sixteam.ssgame.api.recommendation.dto.ResponseRecommendGameListDto;

public interface RecommendedGameService {
    ResponseRecommendGameListDto getRecommendedGameList(Long memberSeq);
}
