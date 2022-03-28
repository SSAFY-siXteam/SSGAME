package com.sixteam.ssgame.api.gameInfo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString(of = {})
@Getter
public class ResponseGameInfoDto {

    private Long gameSeq;

    private String gameName;

    private String shortDescriptionKr;

    private String headerImage;

    private String movies;

    private Double averageRating;

    private Boolean isPlayed;

    private Boolean isRated;

    private Integer memberGameRating;

    private List<String> genres;

    private final String defaultValue = "none";

    @Builder
    public ResponseGameInfoDto(Long gameSeq, String gameName, String shortDescriptionKr, String headerImage, String movies, Double averageRating, Boolean isPlayed, Boolean isRated, Integer memberGameRating, List<String> genres) {
        this.gameSeq = gameSeq;
        this.gameName = gameName;
        this.shortDescriptionKr = (shortDescriptionKr != null) ? shortDescriptionKr : defaultValue;
        this.headerImage = (headerImage != null) ? headerImage : defaultValue;
        this.movies = (movies != null) ? movies : defaultValue;
        this.averageRating = averageRating;
        this.isPlayed = isPlayed;
        this.isRated = isRated;
        this.memberGameRating = memberGameRating;
        this.genres = genres;
    }
}
