package com.sixteam.ssgame.api.member.exception;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;
import com.sixteam.ssgame.global.error.exception.InvalidValueException;

public class SteamIDDuplicateException extends InvalidValueException {

    public SteamIDDuplicateException(String value) {
        super(value, ErrorStatus.STEAMID_DUPLICATION);
    }
}
