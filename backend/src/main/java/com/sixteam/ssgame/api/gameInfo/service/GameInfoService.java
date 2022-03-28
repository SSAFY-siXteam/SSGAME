package com.sixteam.ssgame.api.gameInfo.service;

import com.sixteam.ssgame.api.gameInfo.dto.response.ResponseGameInfoDto;
import org.json.simple.parser.ParseException;

public interface GameInfoService {

    ResponseGameInfoDto findResponseGameInfoDto(Long gameSeq, Long memberSeq) throws ParseException;

}
