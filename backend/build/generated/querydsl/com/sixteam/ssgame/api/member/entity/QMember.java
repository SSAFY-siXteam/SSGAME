package com.sixteam.ssgame.api.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1923295013L;

    public static final QMember member = new QMember("member1");

    public final StringPath avatarUrl = createString("avatarUrl");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> gameCount = createNumber("gameCount", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final BooleanPath isPublic = createBoolean("isPublic");

    public final ListPath<com.sixteam.ssgame.api.analysis.entity.MemberFrequentGenre, com.sixteam.ssgame.api.analysis.entity.QMemberFrequentGenre> memberFrequentGenres = this.<com.sixteam.ssgame.api.analysis.entity.MemberFrequentGenre, com.sixteam.ssgame.api.analysis.entity.QMemberFrequentGenre>createList("memberFrequentGenres", com.sixteam.ssgame.api.analysis.entity.MemberFrequentGenre.class, com.sixteam.ssgame.api.analysis.entity.QMemberFrequentGenre.class, PathInits.DIRECT2);

    public final ListPath<com.sixteam.ssgame.api.gameInfo.entity.MemberGameList, com.sixteam.ssgame.api.gameInfo.entity.QMemberGameList> memberGameLists = this.<com.sixteam.ssgame.api.gameInfo.entity.MemberGameList, com.sixteam.ssgame.api.gameInfo.entity.QMemberGameList>createList("memberGameLists", com.sixteam.ssgame.api.gameInfo.entity.MemberGameList.class, com.sixteam.ssgame.api.gameInfo.entity.QMemberGameList.class, PathInits.DIRECT2);

    public final ListPath<MemberPreferredCategory, QMemberPreferredCategory> memberPreferredCategories = this.<MemberPreferredCategory, QMemberPreferredCategory>createList("memberPreferredCategories", MemberPreferredCategory.class, QMemberPreferredCategory.class, PathInits.DIRECT2);

    public final ListPath<com.sixteam.ssgame.api.recommendation.entity.MemberRecommendedGame, com.sixteam.ssgame.api.recommendation.entity.QMemberRecommendedGame> memberRecommendedGames = this.<com.sixteam.ssgame.api.recommendation.entity.MemberRecommendedGame, com.sixteam.ssgame.api.recommendation.entity.QMemberRecommendedGame>createList("memberRecommendedGames", com.sixteam.ssgame.api.recommendation.entity.MemberRecommendedGame.class, com.sixteam.ssgame.api.recommendation.entity.QMemberRecommendedGame.class, PathInits.DIRECT2);

    public final NumberPath<Long> memberSeq = createNumber("memberSeq", Long.class);

    public final StringPath password = createString("password");

    public final ListPath<com.sixteam.ssgame.api.analysis.entity.RadarChartInfo, com.sixteam.ssgame.api.analysis.entity.QRadarChartInfo> radarChartInfos = this.<com.sixteam.ssgame.api.analysis.entity.RadarChartInfo, com.sixteam.ssgame.api.analysis.entity.QRadarChartInfo>createList("radarChartInfos", com.sixteam.ssgame.api.analysis.entity.RadarChartInfo.class, com.sixteam.ssgame.api.analysis.entity.QRadarChartInfo.class, PathInits.DIRECT2);

    public final StringPath ssgameId = createString("ssgameId");

    public final StringPath steamID = createString("steamID");

    public final StringPath steamNickname = createString("steamNickname");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

