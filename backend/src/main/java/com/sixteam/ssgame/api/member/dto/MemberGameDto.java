package com.sixteam.ssgame.api.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString(of = { "gameSeq", "gameName", "isRated", "memberGameRating", "memberPlayTime", "headerImage" })
@Getter
public class MemberGameDto {

    private Long gameSeq;

    private String gameName;

    private String headerImage;

    private Boolean isRated;

    private Integer memberGameRating;

    private Long memberPlayTime;

    @QueryProjection
    @Builder
    public MemberGameDto(Long gameSeq, String gameName, String headerImage, Boolean isRated, Integer memberGameRating, Long memberPlayTime) {
        this.gameSeq = gameSeq;
        this.gameName = gameName;
        this.headerImage = headerImage;
        this.isRated = isRated;
        this.memberGameRating = memberGameRating;
        this.memberPlayTime = memberPlayTime;
    }
}
