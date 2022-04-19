package com.sixteam.ssgame.api.gameInfo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberPreferredTag is a Querydsl query type for MemberPreferredTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberPreferredTag extends EntityPathBase<MemberPreferredTag> {

    private static final long serialVersionUID = 216241092L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberPreferredTag memberPreferredTag = new QMemberPreferredTag("memberPreferredTag");

    public final com.sixteam.ssgame.api.member.entity.QMember member;

    public final NumberPath<Long> memberTagSeq = createNumber("memberTagSeq", Long.class);

    public final NumberPath<Double> preferredTagRatio = createNumber("preferredTagRatio", Double.class);

    public final QTag tag;

    public QMemberPreferredTag(String variable) {
        this(MemberPreferredTag.class, forVariable(variable), INITS);
    }

    public QMemberPreferredTag(Path<? extends MemberPreferredTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberPreferredTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberPreferredTag(PathMetadata metadata, PathInits inits) {
        this(MemberPreferredTag.class, metadata, inits);
    }

    public QMemberPreferredTag(Class<? extends MemberPreferredTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.sixteam.ssgame.api.member.entity.QMember(forProperty("member")) : null;
        this.tag = inits.isInitialized("tag") ? new QTag(forProperty("tag"), inits.get("tag")) : null;
    }

}

