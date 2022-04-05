package com.sixteam.ssgame.api.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString(of = {"ssgameId", "email", "steamID", "steamNickname", "avatarUrl", "gameCount"})
@Getter
public class ResponseMemberDto {

    private String ssgameId;

    private String email;

    private String steamID;

    private String steamNickname;

    private String avatarUrl;

    private Long gameCount;

    private List<String> preferredCategories;

    @Builder
    public ResponseMemberDto(String ssgameId, String email, String steamID, String steamNickname, String avatarUrl, Long gameCount, List<String> preferredCategories) {
        this.ssgameId = ssgameId;
        this.email = email;
        this.steamID = steamID;
        this.steamNickname = steamNickname;
        this.avatarUrl = avatarUrl;
        this.gameCount = gameCount;
        this.preferredCategories = preferredCategories;
    }
}