package com.sixteam.ssgame.api.gameInfo.service;

import com.sixteam.ssgame.api.gameInfo.dto.response.ResponseGameInfoDto;
import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;

public interface GameInfoService {

    ResponseGameInfoDto findResponseGameInfoDto(Long gameSeq, Long memberSeq);

    String jsonParsingMovies(GameInfo gameInfo);
}
