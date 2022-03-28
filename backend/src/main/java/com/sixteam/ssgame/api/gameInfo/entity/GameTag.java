package com.sixteam.ssgame.api.gameInfo.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_game_tag")
@Entity
public class GameTag {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long gameTagSeq;

    @Column(nullable = false)
    private Long tagCount;

    @Column(nullable = false)
    private Double tagRatio;

    @JoinColumn(name = "game_seq")
    @ManyToOne(fetch = LAZY)
    private GameInfo gameInfo;

    @JoinColumn(name = "tag_seq")
    @ManyToOne(fetch = LAZY)
    private Tag tag;

}
