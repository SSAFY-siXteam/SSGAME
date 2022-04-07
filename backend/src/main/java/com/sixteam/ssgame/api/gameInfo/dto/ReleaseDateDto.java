package com.sixteam.ssgame.api.gameInfo.dto;

import lombok.Builder;

public class ReleaseDateDto {

    private Boolean comingSoon;

    private String date;

    @Builder
    public ReleaseDateDto(Boolean comingSoon, String date) {
        this.comingSoon = comingSoon;
        this.date = date;
    }
}
