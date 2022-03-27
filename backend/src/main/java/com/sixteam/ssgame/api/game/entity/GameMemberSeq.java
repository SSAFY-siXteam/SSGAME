package com.sixteam.ssgame.api.game.entity;

import java.io.Serializable;
import java.util.Objects;

public class GameMemberSeq implements Serializable {

    private Long game;
    private Long member;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameMemberSeq that = (GameMemberSeq) o;
        return Objects.equals(game, that.game) && Objects.equals(member, that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, member);
    }
}
