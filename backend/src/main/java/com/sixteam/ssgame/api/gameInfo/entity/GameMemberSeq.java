package com.sixteam.ssgame.api.gameInfo.entity;

import java.io.Serializable;
import java.util.Objects;

public class GameMemberSeq implements Serializable {

    private final Long gameInfo;
    private final Long member;


    public GameMemberSeq(Long gameInfo, Long member) {
        this.gameInfo = gameInfo;
        this.member = member;
    }

    public GameMemberSeq() {
        this.gameInfo = (long) 0;
        this.member = (long) 0;
    }

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
