package com.sixteam.ssgame.api.member.exception;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;
import com.sixteam.ssgame.global.error.exception.InvalidValueException;

public class PasswordContainedSsgameIdException extends InvalidValueException {

    public PasswordContainedSsgameIdException(String value) {
        super(value, ErrorStatus.PASSWORD_CONTAINED_SSGAMEID);
    }
}
