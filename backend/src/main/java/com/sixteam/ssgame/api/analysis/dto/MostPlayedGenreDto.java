package com.sixteam.ssgame.api.analysis.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MostPlayedGenreDto {

    private String genre;
    private double Ratio;

    @Builder
    public MostPlayedGenreDto(String genre, double ratio) {
        this.genre = genre;
        Ratio = ratio;
    }
}
