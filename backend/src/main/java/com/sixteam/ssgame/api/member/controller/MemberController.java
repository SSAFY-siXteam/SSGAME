package com.sixteam.ssgame.api.member.controller;

import com.sixteam.ssgame.api.member.dto.MemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestLoginMemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestMemberDto;
import com.sixteam.ssgame.api.member.service.MemberService;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;
import com.sixteam.ssgame.global.common.dto.BaseResponseDto;
import com.sixteam.ssgame.global.common.util.JwtTokenUtil;
import com.sixteam.ssgame.global.common.util.LogUtil;
import com.sixteam.ssgame.global.error.exception.CustomException;
import com.sixteam.ssgame.global.error.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.sixteam.ssgame.global.error.dto.ErrorStatus.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public BaseResponseDto register(@Valid @RequestBody RequestMemberDto requestMemberDto, Errors errors) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        System.out.println(requestMemberDto);

        Integer status = null;
        String msg = null;
        Map<String, Object> data = new HashMap<>();

        String password = requestMemberDto.getPassword();

        if (errors.hasErrors()) {
            if (errors.hasFieldErrors()) {
                // field error
                status = HttpStatus.BAD_REQUEST.value();
                data.put("field", errors.getFieldError().getField());
                msg = errors.getFieldError().getDefaultMessage();
            } else {
                throw new CustomException("global error", GLOBAL_ERROR);
            }
        } else if (memberService.hasSsgameId(requestMemberDto.getSsgameId())) {
            throw new InvalidValueException(requestMemberDto.getSsgameId(), SSGAMEID_DUPLICATION);
        } else if (password.contains(requestMemberDto.getSsgameId())) {
            throw new InvalidValueException(requestMemberDto.getSsgameId(), PASSWORD_CONTAINED_SSGAMEID);
        } else if (memberService.hasSteamID(requestMemberDto.getSteamID())) {
            throw new InvalidValueException(requestMemberDto.getSteamID(), STEAMID_DUPLICATION);
        } else if (memberService.hasEmail(requestMemberDto.getEmail())) {
            throw new InvalidValueException(requestMemberDto.getEmail(), EMAIL_DUPLICATION);
        } else {
            try {
                memberService.register(requestMemberDto);
                status = HttpStatus.CREATED.value();
                msg = "회원가입 성공";
            } catch (ParseException e) {
                throw new CustomException("json parse error", JSON_PARSE_ERROR);
            } catch (Exception e) {
                log.error("회원가입 실패 " + e);
                throw new CustomException("회원가입 실패", FAIL_TO_REGISTER);
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
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        Integer status = null;
        String msg = null;

        String regx = "^[a-z]+[0-9a-z]{3,15}$";
        Pattern pattern = Pattern.compile(regx);

        if (!pattern.matcher(ssgameId).matches()) {
            throw new InvalidValueException(ssgameId, INVALID_ID_FORMAT);
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
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        Integer status = null;
        String msg = null;
        Map<String, Object> data = new HashMap<>();

        if (errors.hasErrors()) {
            if (errors.hasFieldErrors()) {
                // field error
                status = HttpStatus.BAD_REQUEST.value();
                data.put("field", errors.getFieldError().getField());
                msg = errors.getFieldError().getDefaultMessage();
            } else {
                throw new CustomException("global error", GLOBAL_ERROR);
            }
        } else {
            // 입력한 아이디가 db에 있는지 확인
            MemberDto memberDto = memberService.findMemberDtoBySsggameId(requestLoginMemberDto.getSsgameId());
            if (memberDto == null) {
                throw new CustomException("member not found", SSGAMEID_NOT_FOUND);
            } else if (!passwordEncoder.matches(requestLoginMemberDto.getPassword(), memberDto.getPassword())) {
                throw new InvalidValueException("wrong password", PASSWORD_NOT_MATCH);
            } else {
                String jwtToken = JwtTokenUtil.getToken(memberDto.getSsgameId());

                status = HttpStatus.OK.value();
                msg = "로그인에 성공했습니다.";
                data.put("steamID", memberDto.getSteamID());
                data.put("jwtToken", jwtToken);
            }
        }

        return BaseResponseDto.builder()
                .status(status)
                .msg(msg)
                .data(data)
                .build();
    }

    @GetMapping("/me")
    public BaseResponseDto me(Authentication authentication) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        Integer status = null;
        String msg = null;
        Map<String, Object> data = new HashMap<>();

        if (authentication == null) {
            throw new CustomException("authentication is null", UNAUTHORIZED_ACCESS);
        } else {
            CustomUserDetails details = (CustomUserDetails) authentication.getDetails();
            String ssgameId = details.getUsername();

            data.put("memberInfo", memberService.findResponseMemberDtoBySsgameId(ssgameId));
            status = HttpStatus.OK.value();
            msg = "회원 정보 조회에 성공했습니다.";
        }

        return BaseResponseDto.builder()
                .status(status)
                .msg(msg)
                .data(data)
                .build();
    }

    @PostMapping("/games")
    public BaseResponseDto loadGames(Authentication authentication, @RequestParam String ssgameId) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        memberService.loadGameInfoBySsgameId(ssgameId);
        memberService.calcMemberPrefferred(ssgameId);

        return BaseResponseDto.builder()
                .status(200)
                .msg("게임 목록 및 가중치 갱신이 완료되었습니다")
                .data(ssgameId)
                .build();
    }
}
