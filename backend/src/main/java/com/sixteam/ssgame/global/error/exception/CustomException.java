package com.sixteam.ssgame.global.error.exception;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;

public class CustomException extends BusinessException {

    public CustomException(String value, ErrorStatus errorStatus) {
        super(value, errorStatus);
    }
}
