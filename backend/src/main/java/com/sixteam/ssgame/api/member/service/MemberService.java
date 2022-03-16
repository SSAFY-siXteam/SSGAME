package com.sixteam.ssgame.api.member.service;

public interface MemberService {

    boolean hasId(String id);

    boolean hasEmail(String email);

    boolean hasSteamID(String steamID);
}
