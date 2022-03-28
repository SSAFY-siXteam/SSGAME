package com.sixteam.ssgame.global.error.exception;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;

public class UnauthorizedAccessException extends InvalidValueException {

    public UnauthorizedAccessException(String value) {
        super(value, ErrorStatus.UNAUTHORIZED_ACCESS);
    }
}
