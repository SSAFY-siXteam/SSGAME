package com.sixteam.ssgame.api.analysis.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRadarChartInfo is a Querydsl query type for RadarChartInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRadarChartInfo extends EntityPathBase<RadarChartInfo> {

    private static final long serialVersionUID = -1591808877L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRadarChartInfo radarChartInfo = new QRadarChartInfo("radarChartInfo");

    public final QCategory category;

    public final NumberPath<Double> categoryRatio = createNumber("categoryRatio", Double.class);

    public final com.sixteam.ssgame.api.member.entity.QMember member;

    public QRadarChartInfo(String variable) {
        this(RadarChartInfo.class, forVariable(variable), INITS);
    }

    public QRadarChartInfo(Path<? extends RadarChartInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRadarChartInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRadarChartInfo(PathMetadata metadata, PathInits inits) {
        this(RadarChartInfo.class, metadata, inits);
    }

    public QRadarChartInfo(Class<? extends RadarChartInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.member = inits.isInitialized("member") ? new com.sixteam.ssgame.api.member.entity.QMember(forProperty("member")) : null;
    }

}

