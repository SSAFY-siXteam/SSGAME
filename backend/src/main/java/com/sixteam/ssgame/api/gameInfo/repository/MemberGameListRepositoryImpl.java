package com.sixteam.ssgame.api.gameInfo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sixteam.ssgame.api.gameInfo.dto.AverageRatingDto;
import com.sixteam.ssgame.api.gameInfo.dto.QAverageRatingDto;

import javax.persistence.EntityManager;

import static com.querydsl.core.types.dsl.MathExpressions.round;
import static com.sixteam.ssgame.api.gameInfo.entity.QMemberGameList.memberGameList;

public class MemberGameListRepositoryImpl implements MemberGameListRepositoryCustom {


    private final JPAQueryFactory queryFactory;

    public MemberGameListRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public AverageRatingDto getAverageRatingByGameSeq(Long gameSeq) {

        return queryFactory
                .select(new QAverageRatingDto(
                        round(memberGameList.memberGameRating.avg(), 2).coalesce(0.0)))
                .from(memberGameList)
                .where(memberGameList.gameInfo.gameSeq.eq(gameSeq))
                .fetchOne();
    }
}
