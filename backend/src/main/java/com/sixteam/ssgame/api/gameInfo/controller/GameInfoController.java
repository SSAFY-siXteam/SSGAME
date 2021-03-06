package com.sixteam.ssgame.api.gameInfo.controller;

import com.sixteam.ssgame.api.gameInfo.dto.request.RequestMemberGameRatingDto;
import com.sixteam.ssgame.api.gameInfo.service.GameInfoService;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;
import com.sixteam.ssgame.global.common.dto.BaseResponseDto;
import com.sixteam.ssgame.global.common.util.LogUtil;
import com.sixteam.ssgame.global.error.dto.ErrorStatus;
import com.sixteam.ssgame.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/games")
@RestController
public class GameInfoController {

    private final GameInfoService gameInfoService;

    @GetMapping("/{gameSeq}")
    public BaseResponseDto get(@PathVariable Long gameSeq, Authentication authentication) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        if (authentication == null) {
            throw new CustomException(LogUtil.getElement(), ErrorStatus.UNAUTHORIZED_ACCESS);
        }

        return BaseResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("게임 정보 조회 성공")
                .data(new HashMap<>() {{
                    put("gameInfo",  gameInfoService.findResponseGameInfoDto(gameSeq, (CustomUserDetails) authentication.getDetails()));
                }})
                .build();
    }

    @PutMapping("/{gameSeq}")
    public BaseResponseDto updateGamePoint(@PathVariable Long gameSeq, Authentication authentication, @RequestBody RequestMemberGameRatingDto requestMemberGameRatingDto) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        if (authentication == null) {
            throw new CustomException(LogUtil.getElement(), ErrorStatus.UNAUTHORIZED_ACCESS);
        }

        gameInfoService.updateMemberGameRating((CustomUserDetails) authentication.getDetails(), gameSeq, requestMemberGameRatingDto.getPoint());

        return BaseResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("별점 입력 및 수정 성공")
                .build();
    }
}
