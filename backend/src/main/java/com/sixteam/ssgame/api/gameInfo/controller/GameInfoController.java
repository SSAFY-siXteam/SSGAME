package com.sixteam.ssgame.api.gameInfo.controller;

import com.sixteam.ssgame.api.gameInfo.service.GameInfoService;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;
import com.sixteam.ssgame.global.common.dto.BaseResponseDto;
import com.sixteam.ssgame.global.common.util.LogUtil;
import com.sixteam.ssgame.global.error.exception.UnauthorizedAccessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
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
            throw new UnauthorizedAccessException("authentication is null");
        } else {
            CustomUserDetails details = (CustomUserDetails) authentication.getDetails();
            Long memberSeq = details.getMember().getMemberSeq();

            try {
                data.put("gameInfo", gameInfoService.findResponseGameInfoDto(gameSeq, memberSeq));
                status = HttpStatus.OK.value();
                msg = "게임 정보 조회 성공";
            } catch (ParseException e) {
                status = HttpStatus.BAD_REQUEST.value();
                msg = "게임 정보 조회 실패 - 처리 해야한다";
                e.printStackTrace();
            }
        }

        return BaseResponseDto.builder()
                .status(status)
                .msg(msg)
                .data(data)
                .build();
    }

}
