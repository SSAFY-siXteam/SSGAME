package com.sixteam.ssgame.api.recommendation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseRecommendedGameInfoDto {
    private Long gameSeq;
    private String gameName;
    private String headerImg;
    private List<String> genres = new ArrayList<>();
    private double averageRating;
    private Integer price;
    private String movies;
    private Integer recommendRanking;

    @Builder
    public ResponseRecommendedGameInfoDto(Long gameSeq, String gameName, String headerImg, List<String> genres, double averageRating, Integer price, String movies, Integer recommendRanking){
        this.gameSeq = gameSeq;
        this.gameName = gameName;
        this.headerImg = headerImg;
        this.genres = genres;
        this.averageRating = averageRating;
        this.price = price;
        this.movies = movies;
        this.recommendRanking = recommendRanking;
    }
}
