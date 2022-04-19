package com.sixteam.ssgame.api.gameInfo.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.sixteam.ssgame.api.gameInfo.dto.QAverageRatingDto is a Querydsl Projection type for AverageRatingDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QAverageRatingDto extends ConstructorExpression<AverageRatingDto> {

    private static final long serialVersionUID = -2097443506L;

    public QAverageRatingDto(com.querydsl.core.types.Expression<Double> averageRating) {
        super(AverageRatingDto.class, new Class<?>[]{double.class}, averageRating);
    }

}

