package com.sixteam.ssgame.api.gameInfo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class AverageRatingDto {

    private Double averageRating;

    @QueryProjection
    public AverageRatingDto(Double averageRating) {
        this.averageRating = averageRating;
    }
}
