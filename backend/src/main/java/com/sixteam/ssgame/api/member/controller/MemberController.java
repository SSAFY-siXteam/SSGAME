package com.sixteam.ssgame.api.member.controller;

import com.sixteam.ssgame.api.member.service.MemberService;
import com.sixteam.ssgame.global.common.dto.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/id/{id}/exist")
    public BaseResponseDto isExistId(@PathVariable String id) {
        log.debug("아이디 중복 검사 api 호출 - MemberController.isExistId()");

        Integer status = null;
        String msg = null;

        String regx = "^[a-z]+[0-9a-z]{3,15}$";
        Pattern pattern = Pattern.compile(regx);

        if (!pattern.matcher(id).matches()) {
            status = HttpStatus.BAD_REQUEST.value();
            msg = "ID가 형식에 맞지 않습니다.";
        } else if (memberService.hasId(id)) {
            status = HttpStatus.OK.value();
            msg = "이미 존재하는 ID입니다.";
        } else {
            status = HttpStatus.NO_CONTENT.value();
            msg = "사용할 수 있는 ID입니다.";
        }

        return BaseResponseDto.builder()
                .status(status)
                .msg(msg)
                .build();
    }
}
