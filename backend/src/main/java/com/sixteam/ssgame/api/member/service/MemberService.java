package com.sixteam.ssgame.api.member.service;

import com.sixteam.ssgame.api.member.dto.request.RequestMemberDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseLoginMemberDto;
import com.sixteam.ssgame.api.member.entity.Member;

public interface MemberService {

    boolean hasSsgameId(String ssgameId);

    boolean hasEmail(String email);

    boolean hasSteamID(String steamID);

    void register(RequestMemberDto requestMemberDto);

    ResponseLoginMemberDto findResponseLoginMemberDtoByMemberSeq(Long memberSeq);

    Member findMemberBySsgameId(String ssgameId);

}
