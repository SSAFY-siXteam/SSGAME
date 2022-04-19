package com.sixteam.ssgame.api.gameInfo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGameTag is a Querydsl query type for GameTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGameTag extends EntityPathBase<GameTag> {

    private static final long serialVersionUID = -28658121L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGameTag gameTag = new QGameTag("gameTag");

    public final QGameInfo gameInfo;

    public final NumberPath<Long> gameTagSeq = createNumber("gameTagSeq", Long.class);

    public final QTag tag;

    public final NumberPath<Long> tagCount = createNumber("tagCount", Long.class);

    public final NumberPath<Double> tagRatio = createNumber("tagRatio", Double.class);

    public QGameTag(String variable) {
        this(GameTag.class, forVariable(variable), INITS);
    }

    public QGameTag(Path<? extends GameTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGameTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGameTag(PathMetadata metadata, PathInits inits) {
        this(GameTag.class, metadata, inits);
    }

    public QGameTag(Class<? extends GameTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gameInfo = inits.isInitialized("gameInfo") ? new QGameInfo(forProperty("gameInfo")) : null;
        this.tag = inits.isInitialized("tag") ? new QTag(forProperty("tag"), inits.get("tag")) : null;
    }

}

