package com.sixteam.ssgame.api.member.service;

import com.sixteam.ssgame.api.member.dto.MemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestLoginMemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestMemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestUpdateMemberDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseMemberDto;
import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;

public interface MemberService {

    boolean hasSsgameId(String ssgameId);

    boolean hasEmail(String email);

    boolean hasSteamID(String steamID);

    boolean register(RequestMemberDto requestMemberDto);

    MemberDto findMemberDtoInLogin(RequestLoginMemberDto requestLoginMemberDto);

    ResponseMemberDto findResponseMemberDto(CustomUserDetails details);

    Member findMemberBySsgameId(String ssgameId);

    boolean renewalMemberData(CustomUserDetails details);

    void calcMemberPreferred(CustomUserDetails details);

    void updateMember(CustomUserDetails details, RequestUpdateMemberDto requestUpdateMemberDto);

    void updateMemberSteamID(CustomUserDetails details, String steamID);

    void deleteMember(String ssgameId);
}
