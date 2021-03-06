package com.sixteam.ssgame.api.member.controller;

import com.sixteam.ssgame.api.gameInfo.service.MemberGameListService;
import com.sixteam.ssgame.api.member.dto.MemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestLoginMemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestMemberDto;
import com.sixteam.ssgame.api.member.dto.request.RequestUpdateMemberDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseMemberGamePageDto;
import com.sixteam.ssgame.api.member.service.MemberService;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;
import com.sixteam.ssgame.global.common.dto.BaseResponseDto;
import com.sixteam.ssgame.global.common.util.JwtTokenUtil;
import com.sixteam.ssgame.global.common.util.LogUtil;
import com.sixteam.ssgame.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.sixteam.ssgame.global.error.dto.ErrorStatus.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    private final MemberGameListService memberGameListService;

    @PostMapping
    public BaseResponseDto register(@Valid @RequestBody RequestMemberDto requestMemberDto, Errors errors) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        Integer status = null;
        String msg = null;
        Map<String, Object> data = new HashMap<>();

        if (errors.hasErrors()) {
            if (errors.hasFieldErrors()) {
                // field error - Request Dto Validation check
                status = BAD_REQUEST.value();
                data.put("field", errors.getFieldError().getField());
                msg = errors.getFieldError().getDefaultMessage();
            } else {
                throw new CustomException(LogUtil.getElement(), GLOBAL_ERROR);
            }
        } else {
            memberService.register(requestMemberDto);
            status = CREATED.value();
            msg = "???????????? ??????";
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

        if (memberService.hasSsgameId(ssgameId)) {
            status = OK.value();
            msg = "?????? ???????????? ID?????????.";
        } else {
            status = NO_CONTENT.value();
            msg = "????????? ??? ?????? ID?????????.";
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
                status = BAD_REQUEST.value();
                data.put("field", errors.getFieldError().getField());
                msg = errors.getFieldError().getDefaultMessage();
            } else {
                throw new CustomException(LogUtil.getElement(), GLOBAL_ERROR);
            }
        } else {
            MemberDto memberDto = memberService.findMemberDtoInLogin(requestLoginMemberDto);
            String jwtToken = JwtTokenUtil.getToken(memberDto.getSsgameId());

            status = OK.value();
            msg = "???????????? ??????????????????.";
            data.put("isPublic", memberDto.getIsPublic());
            data.put("memberSeq", memberDto.getMemberSeq());
            data.put("ssgameId", memberDto.getSsgameId());
            data.put("steamID", memberDto.getSteamID());
            data.put("jwtToken", jwtToken);
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

        if (authentication == null) {
            throw new CustomException(LogUtil.getElement(), UNAUTHORIZED_ACCESS);
        }

        return BaseResponseDto.builder()
                .status(OK.value())
                .msg("?????? ?????? ????????? ??????????????????.")
                .data(new HashMap<>(){{
                    put("memberInfo", memberService.findResponseMemberDto((CustomUserDetails) authentication.getDetails()));
                }})
                .build();
    }

    @GetMapping("/renewal")
    public BaseResponseDto renewal(Authentication authentication) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        Integer status = null;
        String msg = null;

        if (authentication == null) {
            throw new CustomException(LogUtil.getElement(), UNAUTHORIZED_ACCESS);
        }

        if (memberService.renewalMemberData((CustomUserDetails) authentication.getDetails())) {
            status = OK.value();
            msg = "????????? ????????? ?????????????????????.";
        } else {
            status = ACCEPTED.value();
            msg = "????????? ?????? ????????? ??????????????????.";
        }

        return BaseResponseDto.builder()
                .status(status)
                .msg(msg)
                .build();
    }

    // page={page}&size={size}&sort={sort}&filter={filter}&search={search}
    @GetMapping("/games")
    public BaseResponseDto games(Authentication authentication,
                                 Pageable pageable,
                                 @RequestParam boolean filter,
                                 @RequestParam(required = false) String search) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        if (authentication == null) {
            throw new CustomException(LogUtil.getElement(), UNAUTHORIZED_ACCESS);
        }

        CustomUserDetails details = (CustomUserDetails) authentication.getDetails();

        ResponseMemberGamePageDto responseMemberGamePageDto = memberGameListService.getResponseMemberGamePageDto(details, pageable, filter, search);

        return BaseResponseDto.builder()
                .status(OK.value())
                .msg("??? ?????? ????????? ??????????????????.")
                .data(new HashMap<>() {{
                    put("totalCnt", responseMemberGamePageDto.getTotalCnt());
                    put("totalPage", responseMemberGamePageDto.getTotalPage());
                    put("currentPage", responseMemberGamePageDto.getCurrentPage());
                    put("myGameInfos", responseMemberGamePageDto.getMemberGameDtos());
                }})
                .build();
    }

    @PutMapping
    public BaseResponseDto update(Authentication authentication, @Valid @RequestBody RequestUpdateMemberDto requestUpdateMemberDto, Errors errors) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        Integer status = null;
        String msg = null;
        Map<String, Object> data = new HashMap<>();

        if (authentication == null) {
            throw new CustomException(LogUtil.getElement(), UNAUTHORIZED_ACCESS);
        }

        if (errors.hasErrors()) {
            if (errors.hasFieldErrors()) {
                // field error
                status = BAD_REQUEST.value();
                data.put("field", errors.getFieldError().getField());
                msg = errors.getFieldError().getDefaultMessage();
            } else {
                throw new CustomException(LogUtil.getElement(), GLOBAL_ERROR);
            }
        } else {
            memberService.updateMember((CustomUserDetails) authentication.getDetails(), requestUpdateMemberDto);
            status = OK.value();
            msg = "?????? ????????? ??????????????????.";
        }

        return BaseResponseDto.builder()
                .status(status)
                .msg(msg)
                .data(data)
                .build();
    }

    @PutMapping("/steamID")
    public BaseResponseDto updateSteamID(Authentication authentication, @Valid @RequestBody String steamID, Errors errors) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        Integer status = null;
        String msg = null;
        Map<String, Object> data = new HashMap<>();

        if (authentication == null) {
            throw new CustomException(LogUtil.getElement(), UNAUTHORIZED_ACCESS);
        }

        if (errors.hasErrors()) {
            if (errors.hasFieldErrors()) {
                // field error
                status = BAD_REQUEST.value();
                data.put("field", errors.getFieldError().getField());
                msg = errors.getFieldError().getDefaultMessage();
            } else {
                throw new CustomException(LogUtil.getElement(), GLOBAL_ERROR);
            }
        } else {
            memberService.updateMemberSteamID((CustomUserDetails) authentication.getDetails(), steamID);
            status = OK.value();
            msg = "steamID??? ??????????????????.";
        }

        return BaseResponseDto.builder()
                .status(status)
                .msg(msg)
                .data(data)
                .build();
    }

    @DeleteMapping
    public BaseResponseDto DeleteMember(Authentication authentication) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        Integer status = null;
        String msg = null;
        Map<String, Object> data = new HashMap<>();

        if (authentication == null) {
            throw new CustomException("authentication is null", UNAUTHORIZED_ACCESS);
        }

        CustomUserDetails details = (CustomUserDetails) authentication.getDetails();
        String ssgameId = details.getUsername();

        memberService.deleteMember(ssgameId);
        status = OK.value();
        msg = "????????? ?????????????????????.";


        return BaseResponseDto.builder()
                .status(status)
                .msg(msg)
                .data(data)
                .build();
    }
}
