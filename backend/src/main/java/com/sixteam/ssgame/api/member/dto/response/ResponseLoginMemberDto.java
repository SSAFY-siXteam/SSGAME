package com.sixteam.ssgame.api.member.dto.response;

import lombok.Getter;
import lombok.ToString;

@ToString(of = {"memberSeq", "ssgameId", "password", "email", "steamID"})
@Getter
public class ResponseLoginMemberDto {

    private Long memberSeq;

    private String ssgameId;

    private String password;

    private String email;

    private String steamID;

    public ResponseLoginMemberDto(Long memberSeq, String ssgameId, String password, String email, String steamID) {
        this.memberSeq = memberSeq;
        this.ssgameId = ssgameId;
        this.password = password;
        this.email = email;
        this.steamID = steamID;
    }
}
