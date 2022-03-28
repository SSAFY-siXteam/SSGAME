package com.sixteam.ssgame.global.error.exception;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;

public class InvalidValueException extends BusinessException {

    public InvalidValueException(String value) {
        super(value, ErrorStatus.INVALID_INPUT_VALUE);
    }

    public InvalidValueException(String value, ErrorStatus errorStatus) {
        super(value, errorStatus);
    }
}
