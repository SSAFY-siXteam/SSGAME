package com.sixteam.ssgame.api.gameInfo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberGameList is a Querydsl query type for MemberGameList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberGameList extends EntityPathBase<MemberGameList> {

    private static final long serialVersionUID = -930854501L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberGameList memberGameList = new QMemberGameList("memberGameList");

    public final QGameInfo gameInfo;

    public final com.sixteam.ssgame.api.member.entity.QMember member;

    public final NumberPath<Long> memberGameListSeq = createNumber("memberGameListSeq", Long.class);

    public final NumberPath<Integer> memberGameRating = createNumber("memberGameRating", Integer.class);

    public final NumberPath<Long> memberPlayTime = createNumber("memberPlayTime", Long.class);

    public QMemberGameList(String variable) {
        this(MemberGameList.class, forVariable(variable), INITS);
    }

    public QMemberGameList(Path<? extends MemberGameList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberGameList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberGameList(PathMetadata metadata, PathInits inits) {
        this(MemberGameList.class, metadata, inits);
    }

    public QMemberGameList(Class<? extends MemberGameList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gameInfo = inits.isInitialized("gameInfo") ? new QGameInfo(forProperty("gameInfo")) : null;
        this.member = inits.isInitialized("member") ? new com.sixteam.ssgame.api.member.entity.QMember(forProperty("member")) : null;
    }

}

