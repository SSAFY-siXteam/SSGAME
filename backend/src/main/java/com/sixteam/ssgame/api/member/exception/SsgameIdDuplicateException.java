package com.sixteam.ssgame.api.member.exception;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;
import com.sixteam.ssgame.global.error.exception.InvalidValueException;

public class SsgameIdDuplicateException extends InvalidValueException {

    public SsgameIdDuplicateException(String value) {
        super(value, ErrorStatus.SSGAMEID_DUPLICATION);
    }

}
