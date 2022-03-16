package com.sixteam.ssgame.api.member.service;

import com.sixteam.ssgame.api.member.dto.request.RequestMemberDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseLoginMemberDto;

public interface MemberService {

    boolean hasSsgameId(String ssgameId);

    boolean hasEmail(String email);

    boolean hasSteamID(String steamID);

    void register(RequestMemberDto requestMemberDto);

    ResponseLoginMemberDto findResponseLoginMemberDto(String ssgameId);
}
