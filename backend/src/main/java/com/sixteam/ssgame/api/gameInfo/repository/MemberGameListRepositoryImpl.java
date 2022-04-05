package com.sixteam.ssgame.api.gameInfo.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sixteam.ssgame.api.gameInfo.dto.AverageRatingDto;
import com.sixteam.ssgame.api.gameInfo.dto.QAverageRatingDto;
import com.sixteam.ssgame.api.gameInfo.entity.MemberGameList;
import com.sixteam.ssgame.api.member.dto.MemberGameDto;
import com.sixteam.ssgame.api.member.dto.QMemberGameDto;
import com.sixteam.ssgame.api.member.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import java.util.List;

import static com.querydsl.core.types.dsl.MathExpressions.round;
import static com.sixteam.ssgame.api.gameInfo.entity.QGameInfo.gameInfo;
import static com.sixteam.ssgame.api.gameInfo.entity.QMemberGameList.memberGameList;
import static com.sixteam.ssgame.api.member.entity.QMember.member;

public class MemberGameListRepositoryImpl implements MemberGameListRepositoryCustom{

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
                .where(memberGameList.gameInfo.gameSeq.eq(gameSeq).and(memberGameList.memberGameRating.ne(0)))
                .fetchOne();
    }

    @Override
    public Integer countMemberGameListByFilterAndSearch(Long memberSeq, boolean filter, String search) {

        JPAQuery<Integer> query = queryFactory
                .select(memberGameList.count().intValue())
                .from(memberGameList)
                .where(memberGameList.member.memberSeq.eq(memberSeq));

        // filter == true : 미평가 게임만 필터링
        if (filter) {
            query.where(memberGameList.memberGameRating.isNull());
        }

        // 검색어가 있다면 검색
        if (search != null) {
            query.where(gameInfo.gameName.contains(search.trim()));
        }

        return query.fetchOne();
    }

    @Override
    public List<MemberGameDto> findMemberGameDtos(Long memberSeq, Pageable pageable, boolean filter, String search) {

        JPAQuery<MemberGameDto> query = queryFactory
                .select(new QMemberGameDto(
                        gameInfo.gameSeq,
                        gameInfo.gameName,
                        gameInfo.headerImage,
                        memberGameList.memberGameRating.ne(0).as("isRated"),
                        memberGameList.memberGameRating,
                        memberGameList.memberPlayTime))
                .from(memberGameList)
                .join(memberGameList.member, member)
                .join(memberGameList.gameInfo, gameInfo)
                .where(memberGameList.member.memberSeq.eq(memberSeq));

        // filter == true : 미평가 게임만 필터링
        if (filter) {
            query.where(memberGameList.memberGameRating.eq(0));
        }

        // 검색어가 있다면 검색
        if (search != null) {
            query.where(gameInfo.gameName.contains(search));
        }

        // pagination
        query.offset((long) (pageable.getPageNumber() - 1) * pageable.getPageSize())
                .limit(pageable.getPageSize());

        // 정렬
        for (Sort.Order order : pageable.getSort()) {
            if (order.getProperty().equals("dic")) {
                query.orderBy(gameInfo.gameName.asc());
            } else if (order.getProperty().equals("rating")) {
                query.orderBy(memberGameList.memberGameRating.desc());
            }
        }
        query.orderBy(memberGameList.memberPlayTime.desc());

        return query.fetch();
    }

    @Override
    public List<MemberGameList> findMostPlayedGamesByMember(Member member) {
        return queryFactory.select(memberGameList)
                .from(memberGameList)
                .where(memberGameList.member.eq(member)).orderBy(memberGameList.memberPlayTime.desc()).fetch();
    }
}
