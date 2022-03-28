package com.sixteam.ssgame.global.error.exception;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String msg) {
        super(msg, ErrorStatus.ENTITY_NOT_FOUND);
    }
}
