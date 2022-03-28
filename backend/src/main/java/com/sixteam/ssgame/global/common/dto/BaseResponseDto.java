package com.sixteam.ssgame.global.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BaseResponseDto {

    private Integer status;

    private String msg;

    private Object data;

    @Builder
    public BaseResponseDto(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
