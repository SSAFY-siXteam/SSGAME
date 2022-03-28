package com.sixteam.ssgame.api.gameInfo.exception;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;
import com.sixteam.ssgame.global.error.exception.InvalidValueException;

public class GameNotFoundException extends InvalidValueException {

    public GameNotFoundException(Long value) {
        super(Long.toString(value), ErrorStatus.GAME_NOT_FOUND);
    }
}
