package com.sixteam.ssgame.api.gameInfo.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/games")
@RestController
public class GameInfoController {

    private final GameInfoService gameInfoService;

    @GetMapping("/{gameSeq}")
    public BaseResponseDto get(@PathVariable Long gameSeq, Authentication authentication) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        Integer status = null;
        String msg = null;
        Map<String, Object> data = new HashMap<>();

        if (authentication == null) {
            throw new CustomException("authentication is null", ErrorStatus.UNAUTHORIZED_ACCESS);
        } else {
            CustomUserDetails details = (CustomUserDetails) authentication.getDetails();
            Long memberSeq = details.getMember().getMemberSeq();

            data.put("gameInfo", gameInfoService.findResponseGameInfoDto(gameSeq, memberSeq));

            status = HttpStatus.OK.value();
            msg = "게임 정보 조회 성공";
        }

        return BaseResponseDto.builder()
                .status(status)
                .msg(msg)
                .data(data)
                .build();
    }

}
