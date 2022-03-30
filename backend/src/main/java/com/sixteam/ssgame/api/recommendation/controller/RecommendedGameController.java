package com.sixteam.ssgame.api.recommendation.controller;

import com.sixteam.ssgame.api.recommendation.service.RecommendedGameService;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;
import com.sixteam.ssgame.global.common.dto.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/v1/recommend")
@RestController
public class RecommendedGameController {

    @Autowired
    private RecommendedGameService recommendedGameService;

    @GetMapping
    public BaseResponseDto getRecommendedGameList(Authentication authentication) {
        CustomUserDetails details = (CustomUserDetails) authentication.getDetails();

        return BaseResponseDto.builder()
                .status(HttpStatus.OK.value())
                .data(new HashMap<>() {{
                    put("recommendedGameList", recommendedGameService.getRecommendedGameList(details.getMember().getMemberSeq()));
                }})
                .msg("추천 게임 리스트 조회 성공")
                .build();
    }
}