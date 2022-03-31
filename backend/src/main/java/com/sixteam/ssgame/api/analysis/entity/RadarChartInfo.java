package com.sixteam.ssgame.api.analysis.entity;

import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.entity.MemberCategorySeq;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(MemberCategorySeq.class)
@Table(name = "tb_redar_chart_info")
@Entity
public class RadarChartInfo {

    @JoinColumn(name = "member_seq")
    @ManyToOne(fetch = LAZY)
    @Id
    private Member member;

    @JoinColumn(name = "category_seq")
    @ManyToOne(fetch = LAZY)
    @Id
    private Category category;

    @Column(nullable = false)
    private Double categoryRatio;

    @Builder
    public RadarChartInfo(Member member, Category category, Double categoryRatio) {
        this.member = member;
        this.category = category;
        this.categoryRatio = categoryRatio;
    }
}
