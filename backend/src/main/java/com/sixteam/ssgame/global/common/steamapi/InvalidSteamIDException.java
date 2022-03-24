package com.sixteam.ssgame.global.common.steamapi;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;
import com.sixteam.ssgame.global.error.exception.InvalidValueException;

public class InvalidSteamIDException extends InvalidValueException {

    public InvalidSteamIDException(String value) {
        super(value, ErrorStatus.INVALID_STEAMID);
    }
}
