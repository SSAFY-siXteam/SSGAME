package com.sixteam.ssgame.api.member.service;

import com.sixteam.ssgame.api.member.dto.MemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestMemberDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseMemberDto;
import com.sixteam.ssgame.api.member.entity.Member;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface MemberService {

    boolean hasSsgameId(String ssgameId);

    boolean hasEmail(String email);

    boolean hasSteamID(String steamID);

    void register(RequestMemberDto requestMemberDto) throws IOException, ParseException;

    MemberDto findMemberDtoBySsggameId(String ssgameId);

    ResponseMemberDto findResponseMemberDtoBySsgameId(String ssgameId);

    Member findMemberBySsgameId(String ssgameId);

    boolean loadGameInfoBySsgameId(String ssgameId);


    boolean calcMemberPrefferred(String ssgameId);
}
