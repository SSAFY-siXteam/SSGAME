package com.sixteam.ssgame.api.game.entity;

import com.sixteam.ssgame.api.analyze.entity.Category;
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

    private String tagName;

    private String tagNameKr;

    @JoinColumn(name = "category_seq")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
