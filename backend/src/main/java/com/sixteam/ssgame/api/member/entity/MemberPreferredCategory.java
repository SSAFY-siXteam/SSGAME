package com.sixteam.ssgame.api.member.entity;

import com.sixteam.ssgame.api.analyze.entity.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(MemberCategorySeq.class)
@Table(name = "tb_member_preferred_category")
@Entity
public class MemberPreferredCategory {

    @JoinColumn(name = "member_seq", columnDefinition = "BIGINT UNSIGNED")
    @ManyToOne(fetch = LAZY)
    @Id
    private Member member;

    @JoinColumn(name = "category_seq", columnDefinition = "BIGINT UNSIGNED")
    @ManyToOne(fetch = LAZY)
    @Id
    private Category category;

    @Builder
    public MemberPreferredCategory(Member member, Category category) {
        this.member = member;
        this.category = category;
    }
}
