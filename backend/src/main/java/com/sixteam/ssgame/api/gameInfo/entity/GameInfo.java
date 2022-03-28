package com.sixteam.ssgame.api.gameInfo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@ToString(of = {"gameSeq", "steamAppid", "isFree", "shortDescription", "shortDescriptionKr",
        "headerImage", "website", "developers", "publisher", "platforms", "positive", "negative",
        "price", "releaseDate", "movies", "averageForever", "languages", "screenshots", "ownersMin", "ownersMax"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_game_info")
@Entity
public class GameInfo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long gameSeq;

    @Column(nullable = false)
    private String gameName;

    @Column(nullable = false, unique = true)
    private Long steamAppid;

    @Column(nullable = false)
    private Boolean isFree;

    @Column(columnDefinition = "LONGTEXT")
    private String shortDescription;

    @Column(columnDefinition = "LONGTEXT")
    private String shortDescriptionKr;

    private String headerImage;

    private String website;

    private String developers;

    private String publisher;

    private String platforms;

    private Long positive;

    private Long negative;

    private Integer price;

    @Column(columnDefinition = "LONGTEXT")
    private String releaseDate;

    @Column(columnDefinition = "LONGTEXT")
    private String movies;

    private Integer averageForever;

    @Column(columnDefinition = "LONGTEXT")
    private String languages;

    @Column(columnDefinition = "LONGTEXT")
    private String screenshots;

    private Long ownersMin;

    private Long ownersMax;

    @OneToMany(mappedBy = "gameInfo", cascade = ALL)
    private List<MemberGameList> memberGameLists = new ArrayList<>();

    @Builder
    public GameInfo(Long gameSeq, String gameName, Long steamAppid, boolean isFree, String shortDescription, String shortDescriptionKr, String headerImage, String website, String developers, String publisher, String platforms, Long positive, Long negative, Integer price, String releaseDate, String movies, Integer averageForever, String languages, String screenshots, Long ownersMin, Long ownersMax) {
        this.gameSeq = gameSeq;
        this.gameName = gameName;
        this.steamAppid = steamAppid;
        this.isFree = isFree;
        this.shortDescription = shortDescription;
        this.shortDescriptionKr = shortDescriptionKr;
        this.headerImage = headerImage;
        this.website = website;
        this.developers = developers;
        this.publisher = publisher;
        this.platforms = platforms;
        this.positive = positive;
        this.negative = negative;
        this.price = price;
        this.releaseDate = releaseDate;
        this.movies = movies;
        this.averageForever = averageForever;
        this.languages = languages;
        this.screenshots = screenshots;
        this.ownersMin = ownersMin;
        this.ownersMax = ownersMax;
    }
}
