package com.sixteam.ssgame.api.member.service;

import com.sixteam.ssgame.api.member.dto.RequestMemberDto;

public interface MemberService {

    boolean hasSsgameId(String ssgameId);

    boolean hasEmail(String email);

    boolean hasSteamID(String steamID);

    void register(RequestMemberDto requestMemberDto);
}
