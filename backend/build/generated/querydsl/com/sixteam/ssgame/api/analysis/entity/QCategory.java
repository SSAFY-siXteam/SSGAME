package com.sixteam.ssgame.api.analysis.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = 552649227L;

    public static final QCategory category = new QCategory("category");

    public final StringPath categoryName = createString("categoryName");

    public final NumberPath<Long> categorySeq = createNumber("categorySeq", Long.class);

    public final ListPath<com.sixteam.ssgame.api.member.entity.MemberPreferredCategory, com.sixteam.ssgame.api.member.entity.QMemberPreferredCategory> memberPreferredCategories = this.<com.sixteam.ssgame.api.member.entity.MemberPreferredCategory, com.sixteam.ssgame.api.member.entity.QMemberPreferredCategory>createList("memberPreferredCategories", com.sixteam.ssgame.api.member.entity.MemberPreferredCategory.class, com.sixteam.ssgame.api.member.entity.QMemberPreferredCategory.class, PathInits.DIRECT2);

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata metadata) {
        super(Category.class, metadata);
    }

}

