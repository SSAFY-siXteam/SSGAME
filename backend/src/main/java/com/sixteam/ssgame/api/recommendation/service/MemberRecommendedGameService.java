package com.sixteam.ssgame.api.recommendation.service;


import com.sixteam.ssgame.api.recommendation.dto.ResponseMemberRecommendGameListDto;

public interface MemberRecommendedGameService {
    ResponseMemberRecommendGameListDto getRecommendedGameList(Long memberSeq);
}
