package com.sixteam.ssgame.api.gameInfo.service;

import com.sixteam.ssgame.api.gameInfo.dto.response.ResponseGameInfoDto;

public interface GameInfoService {

    ResponseGameInfoDto findResponseGameInfoDto(Long gameSeq, Long memberSeq);

}
