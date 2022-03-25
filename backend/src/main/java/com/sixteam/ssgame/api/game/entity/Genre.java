package com.sixteam.ssgame.api.game.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_genre")
@Entity
public class Genre {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long genreSeq;

    @Column(unique = true, nullable = false)
    private String genreName;

    @Column(unique = true, nullable = false)
    private String genreNameKr;
}
