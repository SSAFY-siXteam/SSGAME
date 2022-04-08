package com.sixteam.ssgame.api.analysis.entity;

import java.io.Serializable;
import java.util.Objects;

public class MemberGenreSeq implements Serializable {

    private Long member;
    private Long genre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberGenreSeq that = (MemberGenreSeq) o;
        return Objects.equals(member, that.member) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, genre);
    }
}
