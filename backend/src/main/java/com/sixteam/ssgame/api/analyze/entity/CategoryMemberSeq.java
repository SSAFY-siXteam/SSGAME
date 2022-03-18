package com.sixteam.ssgame.api.analyze.entity;

import java.io.Serializable;
import java.util.Objects;

public class CategoryMemberSeq implements Serializable {

    private Long category;
    private Long member;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryMemberSeq that = (CategoryMemberSeq) o;
        return Objects.equals(member, that.member) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, category);
    }
}
