package com.sixteam.ssgame.api.gameInfo.repository;

import com.sixteam.ssgame.api.gameInfo.dto.response.AverageRatingDto;

public interface MemberGameListRepositoryCustom {

    AverageRatingDto getAverageRatingByGameSeq(Long gameSeq);

}
