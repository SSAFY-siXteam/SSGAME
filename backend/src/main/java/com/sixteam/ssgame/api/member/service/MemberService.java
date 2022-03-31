package com.sixteam.ssgame.api.member.service;

import com.sixteam.ssgame.api.member.dto.MemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestMemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestUpdateMemberDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseMemberDto;
import com.sixteam.ssgame.api.member.entity.Member;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface MemberService {

    boolean hasSsgameId(String ssgameId);

    boolean hasEmail(String email);

    boolean hasSteamID(String steamID);

    boolean register(RequestMemberDto requestMemberDto) throws IOException, ParseException;

    MemberDto findMemberDtoBySsggameId(String ssgameId);

    ResponseMemberDto findResponseMemberDtoBySsgameId(String ssgameId);

    Member findMemberBySsgameId(String ssgameId);

<<<<<<< HEAD
    boolean loadGameInfoBySsgameId(String ssgameId);


    boolean calcMemberPrefferred(String ssgameId);
=======
    void updateMember(String ssgameId, RequestUpdateMemberDto requestUpdateMemberDto);
>>>>>>> 4202ce6f3b20a4416b70fac36b4c6c96e1dac02c
}
