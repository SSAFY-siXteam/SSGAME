package com.sixteam.ssgame.api.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString(of = {"memberSeq", "ssgameId", "password", "email", "steamID", "steamNickname", "avatarUrl", "isPublic", "gameCount"})
@Getter
public class ResponseLoginMemberDto {

    private Long memberSeq;

    private String ssgameId;

    private String password;

    private String email;

    private String steamID;

    private String steamNickname;

    private String avatarUrl;

    private boolean isPublic;

    private Long gameCount;

    private List<String> preferredCategories;

    @Builder
    public ResponseLoginMemberDto(Long memberSeq, String ssgameId, String password, String email, String steamID, String steamNickname, String avatarUrl,  boolean isPublic, Long gameCount, List<String> preferredCategories) {
        this.memberSeq = memberSeq;
        this.ssgameId = ssgameId;
        this.password = password;
        this.email = email;
        this.steamID = steamID;
        this.steamNickname = steamNickname;
        this.avatarUrl = avatarUrl;
        this.isPublic = isPublic;
        this.gameCount = gameCount;
        this.preferredCategories = preferredCategories;
    }
}
