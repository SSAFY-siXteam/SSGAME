package com.sixteam.ssgame.api.recommendation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberRecommendedGame is a Querydsl query type for MemberRecommendedGame
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberRecommendedGame extends EntityPathBase<MemberRecommendedGame> {

    private static final long serialVersionUID = -1529281975L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberRecommendedGame memberRecommendedGame = new QMemberRecommendedGame("memberRecommendedGame");

    public final com.sixteam.ssgame.api.gameInfo.entity.QGameInfo gameInfo;

    public final com.sixteam.ssgame.api.member.entity.QMember member;

    public final NumberPath<Long> recommendedGameSeq = createNumber("recommendedGameSeq", Long.class);

    public final NumberPath<Double> recommendedRatio = createNumber("recommendedRatio", Double.class);

    public QMemberRecommendedGame(String variable) {
        this(MemberRecommendedGame.class, forVariable(variable), INITS);
    }

    public QMemberRecommendedGame(Path<? extends MemberRecommendedGame> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberRecommendedGame(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberRecommendedGame(PathMetadata metadata, PathInits inits) {
        this(MemberRecommendedGame.class, metadata, inits);
    }

    public QMemberRecommendedGame(Class<? extends MemberRecommendedGame> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gameInfo = inits.isInitialized("gameInfo") ? new com.sixteam.ssgame.api.gameInfo.entity.QGameInfo(forProperty("gameInfo")) : null;
        this.member = inits.isInitialized("member") ? new com.sixteam.ssgame.api.member.entity.QMember(forProperty("member")) : null;
    }

}

