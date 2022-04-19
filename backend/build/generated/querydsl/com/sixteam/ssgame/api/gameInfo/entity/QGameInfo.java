package com.sixteam.ssgame.api.gameInfo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGameInfo is a Querydsl query type for GameInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGameInfo extends EntityPathBase<GameInfo> {

    private static final long serialVersionUID = -888716879L;

    public static final QGameInfo gameInfo = new QGameInfo("gameInfo");

    public final NumberPath<Integer> averageForever = createNumber("averageForever", Integer.class);

    public final StringPath developers = createString("developers");

    public final ListPath<GameGenre, QGameGenre> gameGenres = this.<GameGenre, QGameGenre>createList("gameGenres", GameGenre.class, QGameGenre.class, PathInits.DIRECT2);

    public final StringPath gameName = createString("gameName");

    public final NumberPath<Long> gameSeq = createNumber("gameSeq", Long.class);

    public final StringPath headerImage = createString("headerImage");

    public final BooleanPath isFree = createBoolean("isFree");

    public final StringPath languages = createString("languages");

    public final ListPath<MemberGameList, QMemberGameList> memberGameLists = this.<MemberGameList, QMemberGameList>createList("memberGameLists", MemberGameList.class, QMemberGameList.class, PathInits.DIRECT2);

    public final StringPath movies = createString("movies");

    public final NumberPath<Long> negative = createNumber("negative", Long.class);

    public final NumberPath<Long> ownersMax = createNumber("ownersMax", Long.class);

    public final NumberPath<Long> ownersMin = createNumber("ownersMin", Long.class);

    public final StringPath platforms = createString("platforms");

    public final NumberPath<Long> positive = createNumber("positive", Long.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath publisher = createString("publisher");

    public final StringPath releaseDate = createString("releaseDate");

    public final StringPath screenshots = createString("screenshots");

    public final StringPath shortDescription = createString("shortDescription");

    public final StringPath shortDescriptionKr = createString("shortDescriptionKr");

    public final NumberPath<Long> steamAppid = createNumber("steamAppid", Long.class);

    public final StringPath website = createString("website");

    public QGameInfo(String variable) {
        super(GameInfo.class, forVariable(variable));
    }

    public QGameInfo(Path<? extends GameInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGameInfo(PathMetadata metadata) {
        super(GameInfo.class, metadata);
    }

}

