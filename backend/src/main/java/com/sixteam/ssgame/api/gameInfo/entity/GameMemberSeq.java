package com.sixteam.ssgame.api.gameInfo.entity;

import java.io.Serializable;
import java.util.Objects;

public class GameMemberSeq implements Serializable {

    private Long gameInfo;
    private Long member;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameMemberSeq that = (GameMemberSeq) o;
        return Objects.equals(gameInfo, that.gameInfo) && Objects.equals(member, that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameInfo, member);
    }
}
