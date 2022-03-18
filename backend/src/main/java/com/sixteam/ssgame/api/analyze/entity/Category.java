package com.sixteam.ssgame.api.analyze.entity;

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

    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long categorySeq;

    // enum type으로 category name을 지정하고 싶었으나 실패
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, unique = true)
//    private CategoryType categoryName;

    @Column(nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = ALL)
    private List<MemberPreferredCategory> memberPreferredCategories = new ArrayList<>();

}
