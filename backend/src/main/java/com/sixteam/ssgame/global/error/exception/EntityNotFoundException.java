package com.sixteam.ssgame.global.error.exception;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;

/**
 * 사용 자제
 *
 */
public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String msg) {
        super(msg, ErrorStatus.ENTITY_NOT_FOUND);
    }
}
