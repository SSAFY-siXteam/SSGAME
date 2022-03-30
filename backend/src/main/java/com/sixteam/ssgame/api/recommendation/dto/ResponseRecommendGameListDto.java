package com.sixteam.ssgame.api.recommendation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseRecommendGameListDto {
    List<ResponseRecommendedGameInfoDto> responseRecommendedGameInfoDtos = new ArrayList<>();

    @Builder
    public ResponseRecommendGameListDto(List<ResponseRecommendedGameInfoDto> responseRecommendedGameInfoDtos){
        this.responseRecommendedGameInfoDtos = responseRecommendedGameInfoDtos;
    }
}
