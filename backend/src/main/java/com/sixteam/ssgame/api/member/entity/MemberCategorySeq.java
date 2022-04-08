package com.sixteam.ssgame.api.member.entity;

import java.io.Serializable;
import java.util.Objects;

public class MemberCategorySeq implements Serializable {

    private Long member;
    private Long category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberCategorySeq that = (MemberCategorySeq) o;
        return Objects.equals(member, that.member) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, category);
    }
}
