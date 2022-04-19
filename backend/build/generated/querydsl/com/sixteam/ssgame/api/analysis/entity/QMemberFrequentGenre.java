package com.sixteam.ssgame.api.analysis.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberFrequentGenre is a Querydsl query type for MemberFrequentGenre
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberFrequentGenre extends EntityPathBase<MemberFrequentGenre> {

    private static final long serialVersionUID = -1294645330L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberFrequentGenre memberFrequentGenre = new QMemberFrequentGenre("memberFrequentGenre");

    public final com.sixteam.ssgame.api.gameInfo.entity.QGenre genre;

    public final NumberPath<Long> genreCount = createNumber("genreCount", Long.class);

    public final com.sixteam.ssgame.api.member.entity.QMember member;

    public QMemberFrequentGenre(String variable) {
        this(MemberFrequentGenre.class, forVariable(variable), INITS);
    }

    public QMemberFrequentGenre(Path<? extends MemberFrequentGenre> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberFrequentGenre(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberFrequentGenre(PathMetadata metadata, PathInits inits) {
        this(MemberFrequentGenre.class, metadata, inits);
    }

    public QMemberFrequentGenre(Class<? extends MemberFrequentGenre> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.genre = inits.isInitialized("genre") ? new com.sixteam.ssgame.api.gameInfo.entity.QGenre(forProperty("genre")) : null;
        this.member = inits.isInitialized("member") ? new com.sixteam.ssgame.api.member.entity.QMember(forProperty("member")) : null;
    }

}

