package com.sixteam.ssgame.api.analysis.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MostPlayedGamesDto {

    private Long gameSeq;

    private String gameName;

    private String headerImage;

    private String[] genres;

    @Builder
    public MostPlayedGamesDto(Long gameSeq, String gameName, String headerImage, String[] genres) {
        this.gameSeq = gameSeq;
        this.gameName = gameName;
        this.headerImage = headerImage;
        this.genres = genres;
    }
}
