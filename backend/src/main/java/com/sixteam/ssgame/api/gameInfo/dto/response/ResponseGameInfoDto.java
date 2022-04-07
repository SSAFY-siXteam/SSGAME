package com.sixteam.ssgame.api.gameInfo.dto.response;

import com.sixteam.ssgame.api.gameInfo.dto.ReleaseDateDto;
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

    private Integer averageForever;

    private List<String> platforms;

    private String website;

    private List<String> languages;

    private List<String> publisher;

    private List<String> developers;

    private ReleaseDateDto releaseDate;

    @Builder
    public ResponseGameInfoDto(Long gameSeq, String gameName, String shortDescriptionKr, String headerImage, String movies, Double averageRating, Boolean isPlayed, Boolean isRated, Integer memberGameRating, List<String> genres, Integer averageForever, List<String> platforms, String website, List<String> languages, List<String> publisher, List<String> developers, ReleaseDateDto releaseDate) {
        this.gameSeq = gameSeq;
        this.gameName = gameName;
        this.shortDescriptionKr = shortDescriptionKr;
        this.headerImage = headerImage;
        this.movies = movies;
        this.averageRating = averageRating;
        this.isPlayed = isPlayed;
        this.isRated = isRated;
        this.memberGameRating = memberGameRating;
        this.genres = genres;
        this.averageForever = averageForever;
        this.platforms = platforms;
        this.website = website;
        this.languages = languages;
        this.publisher = publisher;
        this.developers = developers;
        this.releaseDate = releaseDate;
    }
}
