package com.sixteam.ssgame.api.gameInfo.service;

import com.sixteam.ssgame.api.member.dto.response.ResponseMemberGamePageDto;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;
import org.springframework.data.domain.Pageable;

public interface MemberGameListService {

    ResponseMemberGamePageDto getResponseMemberGamePageDto(CustomUserDetails details, Pageable pageable, boolean filter, String search);

}
