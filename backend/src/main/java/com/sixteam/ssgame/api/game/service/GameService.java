package com.sixteam.ssgame.api.game.service;

import com.sixteam.ssgame.api.game.dto.response.ResponseGameDto;
import org.json.simple.parser.ParseException;

public interface GameService {

    ResponseGameDto findResponseGameDto(Long gameSeq, Long memberSeq) throws ParseException;

}
