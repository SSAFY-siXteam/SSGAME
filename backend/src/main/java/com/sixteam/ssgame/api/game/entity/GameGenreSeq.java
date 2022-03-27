package com.sixteam.ssgame.api.game.entity;

import java.io.Serializable;
import java.util.Objects;

public class GameGenreSeq implements Serializable {

    private Long game;
    private Long genre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameGenreSeq that = (GameGenreSeq) o;
        return Objects.equals(game, that.game) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, genre);
    }
}
