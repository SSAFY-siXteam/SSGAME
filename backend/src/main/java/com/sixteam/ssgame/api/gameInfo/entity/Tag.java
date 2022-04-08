package com.sixteam.ssgame.api.gameInfo.entity;

import com.sixteam.ssgame.api.analysis.entity.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_tag")
@Entity
public class Tag {

    @JoinColumn(name = "tag_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long tagSeq;

    @Column(unique = true, nullable = false)
    private String tagName;

    @Column(unique = true, nullable = false)
    private String tagNameKr;

    @JoinColumn(name = "category_seq")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

}
