package com.sixteam.ssgame.api.game.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_game_tag")
@Entity
public class GameTag {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long gameTagSeq;

    @JoinColumn(name = "game_seq")
    @ManyToOne(fetch = FetchType.LAZY)
    private GameInfo gameInfo;

    @JoinColumn(name = "tag_seq")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    private Long tagCount;

    private Long tagRatio;

}
