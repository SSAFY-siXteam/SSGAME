package com.sixteam.ssgame.api.analysis.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sixteam.ssgame.api.analysis.entity.MemberFrequentGenre;
import com.sixteam.ssgame.api.member.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

import static com.sixteam.ssgame.api.analysis.entity.QMemberFrequentGenre.memberFrequentGenre;

public class MemberFrequentGenreRepositoryImpl implements MemberFrequentGenreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberFrequentGenreRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberFrequentGenre> findMostPlayedGenresByMember(Member member) {

        return queryFactory.select(memberFrequentGenre)
                .from(memberFrequentGenre)
                .where(memberFrequentGenre.member.eq(member))
                .orderBy(memberFrequentGenre.genreCount.desc())
                .fetch();
    }
}

