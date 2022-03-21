package com.sixteam.ssgame.api.member.controller;

import com.sixteam.ssgame.api.member.dto.request.RequestLoginMemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestMemberDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseLoginMemberDto;
import com.sixteam.ssgame.api.member.service.MemberService;
import com.sixteam.ssgame.global.common.dto.BaseResponseDto;
import com.sixteam.ssgame.global.common.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public BaseResponseDto register(@Valid @RequestBody RequestMemberDto requestMemberDto, Errors errors) {
        log.debug("회원 가입 api 호출 - MemberController.register()");

        Integer status = null;
        String msg = null;
        Map<String, Object> data = new HashMap<>();

        String password = requestMemberDto.getPassword();

        if (errors.hasErrors()) {
            status = HttpStatus.BAD_REQUEST.value();
            if (errors.hasFieldErrors()) {
                // field error
                data.put("field", errors.getFieldError().getField());
                msg = errors.getFieldError().getDefaultMessage();
            } else {
                // global error
                msg = "global error";
            }
        } else if (memberService.hasSsgameId(requestMemberDto.getSsgameId())) {
            // 아이디 중복
            status = HttpStatus.CONFLICT.value();
            msg = "이미 존재하는 ID입니다.";
            data.put("field", "ssgameId");
        } else if (password.contains(requestMemberDto.getSsgameId())) {
            // 패스워드에 아이디가 포함된 경우
            status = HttpStatus.BAD_REQUEST.value();
            msg = "패스워드에 아이디가 포함될 수 없습니다.";
            data.put("field", "ssgameId into password");
        } else if (memberService.hasSteamID(requestMemberDto.getSteamID())) {
            // 스팀 아이디 중복
            status = HttpStatus.CONFLICT.value();
            msg = "해당 Steam ID로 가입한 계정이 존재합니다.";
            data.put("field", "steamID");
        } else if (memberService.hasEmail(requestMemberDto.getEmail())) {
            // 이메일 중복
            status = HttpStatus.CONFLICT.value();
            msg = "해당 이메일로 가입한 계정이 존재합니다.";
            data.put("field", "email");
        } else {
            try {
                memberService.register(requestMemberDto);

                status = HttpStatus.CREATED.value();
                msg = "회원가입 성공";
            } catch (Exception e) {
                log.error("회원가입 실패");

                status = HttpStatus.INTERNAL_SERVER_ERROR.value();
                msg = "회원가입 실패";
            }
        }

        return BaseResponseDto.builder()
                .status(status)
                .msg(msg)
                .data(data)
                .build();
    }

    @GetMapping("/ssgameId/{ssgameId}/exist")
    public BaseResponseDto isExistSsgameId(@PathVariable String ssgameId) {
        log.debug("아이디 중복 검사 api 호출 - MemberController.isExistSsgameId()");

        Integer status = null;
        String msg = null;

        String regx = "^[a-z]+[0-9a-z]{3,15}$";
        Pattern pattern = Pattern.compile(regx);

        if (!pattern.matcher(ssgameId).matches()) {
            status = HttpStatus.BAD_REQUEST.value();
            msg = "ID가 형식에 맞지 않습니다.";
        } else if (memberService.hasSsgameId(ssgameId)) {
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

    @PostMapping("/login")
    public BaseResponseDto login(@Valid @RequestBody RequestLoginMemberDto requestLoginMemberDto, Errors errors) {
        log.debug("로그인 api 호출 - MemberController.login()");

        Integer status = null;
        String msg = null;
        Map<String, Object> data = new HashMap<>();

        if (errors.hasErrors()) {
            status = HttpStatus.BAD_REQUEST.value();
            if (errors.hasFieldErrors()) {
                // field error
                data.put("field", errors.getFieldError().getField());
                msg = errors.getFieldError().getDefaultMessage();
            } else {
                // global error
                msg = "global error";
            }
        } else {
            // 입력한 아이디가 db에 있는지 확인
            ResponseLoginMemberDto responseLoginMemberDto = memberService.findResponseLoginMemberDto(requestLoginMemberDto.getSsgameId());
            if (responseLoginMemberDto == null) {
                // 해당 아이디로 검색된 회원이 없는 경우
                status = HttpStatus.NO_CONTENT.value();
                msg = "아이디를 다시 확인해주세요.";
            } else if (!passwordEncoder.matches(requestLoginMemberDto.getPassword(), responseLoginMemberDto.getPassword())) {
                // db에 있는 비밀번호와 입력한 비밀번호가 일치하지 않는 경우
                status = HttpStatus.UNAUTHORIZED.value();
                msg = "비밀번호를 다시 확인해주세요.";
            } else {
                String jwtToken = JwtTokenUtil.getToken(responseLoginMemberDto.getSsgameId());

                status = HttpStatus.OK.value();
                msg = "로그인에 성공했습니다.";
                data.put("memberInfo", responseLoginMemberDto);
                data.put("jwtToken", jwtToken);
            }
        }

        return BaseResponseDto.builder()
                .status(status)
                .msg(msg)
                .data(data)
                .build();
    }
}