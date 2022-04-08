package com.sixteam.ssgame.api.gameInfo.service;

import com.sixteam.ssgame.api.gameInfo.dto.response.ResponseGameInfoDto;
import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;

public interface GameInfoService {

    ResponseGameInfoDto findResponseGameInfoDto(Long gameSeq, CustomUserDetails details);

    String jsonParsingMovies(GameInfo gameInfo);

    void updateMemberGameRating(CustomUserDetails details, Long gameSeq, Integer memberGameRating);

}
