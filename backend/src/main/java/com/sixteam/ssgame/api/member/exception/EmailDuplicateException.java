package com.sixteam.ssgame.api.member.exception;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;
import com.sixteam.ssgame.global.error.exception.InvalidValueException;

public class EmailDuplicateException extends InvalidValueException {

    public EmailDuplicateException(String email) {
        super(email, ErrorStatus.EMAIL_DUPLICATION);
    }
}
