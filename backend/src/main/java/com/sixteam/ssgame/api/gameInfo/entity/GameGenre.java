package com.sixteam.ssgame.api.gameInfo.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(GameGenreSeq.class)
@Table(name = "tb_game_genre")
@Entity
public class GameGenre {

    @JoinColumn(name = "game_seq")
    @ManyToOne(fetch = LAZY)
    @Id
    private GameInfo gameInfo;

    @JoinColumn(name = "genre_seq")
    @ManyToOne(fetch = LAZY)
    @Id
    private Genre genre;

}

