package com.sixteam.ssgame.api.gameInfo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sixteam.ssgame.api.gameInfo.entity.Tag;

import javax.persistence.EntityManager;
import java.util.List;

import static com.sixteam.ssgame.api.gameInfo.entity.QTag.tag;

public class TagRepositoryImpl implements TagRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TagRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Tag> findAll() {

        return queryFactory
                .select(tag)
                .from(tag)
                .fetch();
    }
}
