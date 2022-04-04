package com.sixteam.ssgame.api.recommendation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseMemberRecommendGameListDto {
<<<<<<< HEAD
=======

>>>>>>> 96959a4f3f9bd7bab214fd0ce56f0f6f546d481d
    List<ResponseMemberRecommendedGameInfoDto> responseMemberRecommendedGameInfoDtos = new ArrayList<>();

    @Builder
    public ResponseMemberRecommendGameListDto(List<ResponseMemberRecommendedGameInfoDto> responseMemberRecommendedGameInfoDtos){
        this.responseMemberRecommendedGameInfoDtos = responseMemberRecommendedGameInfoDtos;
    }
}
