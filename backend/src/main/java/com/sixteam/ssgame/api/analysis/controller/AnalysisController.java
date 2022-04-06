package com.sixteam.ssgame.api.analysis.controller;

import com.sixteam.ssgame.api.analysis.service.AnalysisService;
import com.sixteam.ssgame.api.member.service.MemberService;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;
import com.sixteam.ssgame.global.common.dto.BaseResponseDto;
import com.sixteam.ssgame.global.common.util.LogUtil;
import com.sixteam.ssgame.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.sixteam.ssgame.global.error.dto.ErrorStatus.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/analysis")
@RestController
public class AnalysisController {

    private final AnalysisService analysisService;

    private final MemberService memberService;

    @Transactional
    @GetMapping("/graph")
    public BaseResponseDto getGraph(Authentication authentication) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        if (authentication == null) {
            throw new CustomException(LogUtil.getElement(), UNAUTHORIZED_ACCESS);
        }

        CustomUserDetails details = (CustomUserDetails) authentication.getDetails();

        Map<String, Object> data = new HashMap<>();
        data.put("userNickName", memberService.findMemberBySsgameId(details.getUsername()).getSteamNickname());
        data.put("categories", analysisService.getGraph(details.getMember().getMemberSeq()));
        return BaseResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("그래프 정보 조회 성공")
                .data(data)
                .build();
    }

    @GetMapping("/genres")
    public BaseResponseDto getMostPlayedGenre(Authentication authentication) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        if (authentication == null) {
            throw new CustomException(LogUtil.getElement(), UNAUTHORIZED_ACCESS);
        }

        CustomUserDetails details = (CustomUserDetails) authentication.getDetails();

        return BaseResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("가장 많이 플레이한 장르 조회 성공")
                .data(new HashMap<>() {{
                    put("MostPlayedGenres", analysisService.getMostPlayedGenres(details.getMember().getMemberSeq()));
                }})
                .build();
    }

    @GetMapping("/games")
    public BaseResponseDto getMostPlayedGames(Authentication authentication) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        if (authentication == null) {
            throw new CustomException(LogUtil.getElement(), UNAUTHORIZED_ACCESS);
        }

        CustomUserDetails details = (CustomUserDetails) authentication.getDetails();

        return BaseResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("가장 많이 플레이한 장르 게임 성공")
                .data(new HashMap<>() {{
                    put("mostPlayedGames", analysisService.getMostPlayedGames(details.getMember().getMemberSeq()));
                }})
                .build();
    }
}
