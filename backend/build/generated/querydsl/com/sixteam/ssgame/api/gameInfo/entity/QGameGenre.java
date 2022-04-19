package com.sixteam.ssgame.api.gameInfo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGameGenre is a Querydsl query type for GameGenre
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGameGenre extends EntityPathBase<GameGenre> {

    private static final long serialVersionUID = -1782526752L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGameGenre gameGenre = new QGameGenre("gameGenre");

    public final QGameInfo gameInfo;

    public final QGenre genre;

    public QGameGenre(String variable) {
        this(GameGenre.class, forVariable(variable), INITS);
    }

    public QGameGenre(Path<? extends GameGenre> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGameGenre(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGameGenre(PathMetadata metadata, PathInits inits) {
        this(GameGenre.class, metadata, inits);
    }

    public QGameGenre(Class<? extends GameGenre> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gameInfo = inits.isInitialized("gameInfo") ? new QGameInfo(forProperty("gameInfo")) : null;
        this.genre = inits.isInitialized("genre") ? new QGenre(forProperty("genre")) : null;
    }

}

