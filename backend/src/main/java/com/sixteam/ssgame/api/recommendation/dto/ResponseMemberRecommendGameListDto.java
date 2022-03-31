package com.sixteam.ssgame.api.recommendation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseMemberRecommendGameListDto {
    List<ResponseMemberRecommendedGameInfoDto> responseMemberRecommendedGameInfoDtos = new ArrayList<>();

    @Builder
    public ResponseMemberRecommendGameListDto(List<ResponseMemberRecommendedGameInfoDto> responseMemberRecommendedGameInfoDtos){
        this.responseMemberRecommendedGameInfoDtos = responseMemberRecommendedGameInfoDtos;
    }
}
