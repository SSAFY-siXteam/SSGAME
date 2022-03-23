package com.sixteam.ssgame.global.error.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorStatus {

    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), " Invalid Input Value"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), " Invalid Input Value"),
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), " Entity Not Found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server Error"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST.value(), " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN.value(), "Access is Denied"),
//    GLOBAL_ERROR(HttpStatus.BAD_REQUEST.value(), "Global Error in Post Mapping"),
//    FIELD_ERROR(HttpStatus.BAD_REQUEST.value(), "Field Error in Post Mapping"),

    // Member
    SSGAMEID_DUPLICATION(HttpStatus.CONFLICT.value(), "이미 존재하는 ID입니다."),
    EMAIL_DUPLICATION(HttpStatus.CONFLICT.value(), "해당 이메일로 가입한 계정이 존재합니다."),         // 409, "Email is Duplication"
    STEAMID_DUPLICATION(HttpStatus.CONFLICT.value(), "해당 Steam ID로 가입한 계정이 존재합니다."),    // 409, "Steam ID is Duplication"
    PASSWORD_CONTAINED_SSGAMEID(HttpStatus.BAD_REQUEST.value(), "패스워드에 아이디가 포함될 수 없습니다."),          // "ssgameId into password"

    LOGIN_INPUT_INVALID(HttpStatus.BAD_REQUEST.value(), "Login input is invalid"),

    // API
    API_NOT_CONNECTION(HttpStatus.BAD_GATEWAY.value(), "Fail to connect")
    ;

    private Integer status;

    private final String msg;

    ErrorStatus(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }
}
