package com.sixteam.ssgame.api.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberPreferredCategory is a Querydsl query type for MemberPreferredCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberPreferredCategory extends EntityPathBase<MemberPreferredCategory> {

    private static final long serialVersionUID = -1418345478L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberPreferredCategory memberPreferredCategory = new QMemberPreferredCategory("memberPreferredCategory");

    public final com.sixteam.ssgame.api.analysis.entity.QCategory category;

    public final QMember member;

    public QMemberPreferredCategory(String variable) {
        this(MemberPreferredCategory.class, forVariable(variable), INITS);
    }

    public QMemberPreferredCategory(Path<? extends MemberPreferredCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberPreferredCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberPreferredCategory(PathMetadata metadata, PathInits inits) {
        this(MemberPreferredCategory.class, metadata, inits);
    }

    public QMemberPreferredCategory(Class<? extends MemberPreferredCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.sixteam.ssgame.api.analysis.entity.QCategory(forProperty("category")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

