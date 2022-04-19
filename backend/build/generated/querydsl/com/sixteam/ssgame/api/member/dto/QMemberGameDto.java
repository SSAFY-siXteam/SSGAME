package com.sixteam.ssgame.api.member.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.sixteam.ssgame.api.member.dto.QMemberGameDto is a Querydsl Projection type for MemberGameDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberGameDto extends ConstructorExpression<MemberGameDto> {

    private static final long serialVersionUID = -456789340L;

    public QMemberGameDto(com.querydsl.core.types.Expression<Long> gameSeq, com.querydsl.core.types.Expression<String> gameName, com.querydsl.core.types.Expression<String> headerImage, com.querydsl.core.types.Expression<Boolean> isRated, com.querydsl.core.types.Expression<Integer> memberGameRating, com.querydsl.core.types.Expression<Long> memberPlayTime) {
        super(MemberGameDto.class, new Class<?>[]{long.class, String.class, String.class, boolean.class, int.class, long.class}, gameSeq, gameName, headerImage, isRated, memberGameRating, memberPlayTime);
    }

}

