package com.sixteam.ssgame.api.gameInfo.service;

import com.sixteam.ssgame.api.member.dto.response.ResponseMemberGamePageDto;
import org.springframework.data.domain.Pageable;

public interface MemberGameListService {

    ResponseMemberGamePageDto getResponseMemberGamePageDto(Long memberSeq, Pageable pageable, boolean filter, String search);

}
