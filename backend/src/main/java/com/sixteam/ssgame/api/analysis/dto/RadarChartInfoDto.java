package com.sixteam.ssgame.api.analysis.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RadarChartInfoDto {

    private String subject;

    private double categoryRatio;

    @Builder
    public RadarChartInfoDto(String subject, double categoryRatio) {
        this.subject = subject;
        this.categoryRatio = categoryRatio;
    }
}
