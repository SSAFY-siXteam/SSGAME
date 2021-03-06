package com.sixteam.ssgame.api.analysis.entity;

import com.sixteam.ssgame.api.member.entity.MemberPreferredCategory;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@ToString(of = {"categorySeq", "categoryName"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_category")
@Entity
public class Category {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long categorySeq;

    @Column(unique = true, nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = ALL)
    private List<MemberPreferredCategory> memberPreferredCategories = new ArrayList<>();

    @Builder
    public Category(Long categorySeq, String categoryName) {
        this.categorySeq = categorySeq;
        this.categoryName = categoryName;
    }
}
