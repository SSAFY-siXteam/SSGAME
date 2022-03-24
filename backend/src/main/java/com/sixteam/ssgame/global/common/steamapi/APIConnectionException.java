package com.sixteam.ssgame.global.common.steamapi;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;
import com.sixteam.ssgame.global.error.exception.BusinessException;

public class APIConnectionException extends BusinessException {

    public APIConnectionException(String msg) {
        super(msg, ErrorStatus.API_NOT_CONNECTION);
    }
}
