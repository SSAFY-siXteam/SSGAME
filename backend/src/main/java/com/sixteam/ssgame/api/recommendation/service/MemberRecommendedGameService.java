package com.sixteam.ssgame.api.recommendation.service;

import com.sixteam.ssgame.api.recommendation.dto.ResponseMemberRecommendedGameInfoDto;

import java.util.List;

public interface MemberRecommendedGameService {

    List<ResponseMemberRecommendedGameInfoDto> getRecommendedGameList(Long memberSeq);

}
