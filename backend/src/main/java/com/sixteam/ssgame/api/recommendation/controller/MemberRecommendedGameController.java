package com.sixteam.ssgame.api.recommendation.controller;

import com.sixteam.ssgame.api.recommendation.service.MemberRecommendedGameService;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;
import com.sixteam.ssgame.global.common.dto.BaseResponseDto;
import com.sixteam.ssgame.global.error.dto.ErrorStatus;
import com.sixteam.ssgame.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequiredArgsConstructor
@RequestMapping("/api/v1/recommend")
@RestController
public class MemberRecommendedGameController {

    private final MemberRecommendedGameService memberRecommendedGameService;

    @GetMapping
    public BaseResponseDto getRecommendedGameList(Authentication authentication) {
        if (authentication == null) {
            throw new CustomException("authentication is null", ErrorStatus.UNAUTHORIZED_ACCESS);
        }

        CustomUserDetails details = (CustomUserDetails) authentication.getDetails();
        return BaseResponseDto.builder()
                .status(HttpStatus.OK.value())
                .data(new HashMap<>() {{
                    put("recommendedGameList", memberRecommendedGameService.getRecommendedGameList(details.getMember().getMemberSeq()));
                }})
                .msg("추천 게임 리스트 조회 성공")
                .build();
    }
}
