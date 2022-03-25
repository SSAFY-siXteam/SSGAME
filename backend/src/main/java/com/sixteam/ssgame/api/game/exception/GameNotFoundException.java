package com.sixteam.ssgame.api.game.exception;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;
import com.sixteam.ssgame.global.error.exception.InvalidValueException;

public class GameNotFoundException extends InvalidValueException {

    public GameNotFoundException(String value) {
        super(value, ErrorStatus.GAME_NOT_FOUND);
    }
}
