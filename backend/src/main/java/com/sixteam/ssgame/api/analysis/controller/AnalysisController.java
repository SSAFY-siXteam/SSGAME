package com.sixteam.ssgame.api.analysis.controller;

import com.sixteam.ssgame.api.analysis.dto.RadarChartInfoDto;
import com.sixteam.ssgame.api.analysis.entity.RadarChartInfo;
import com.sixteam.ssgame.api.analysis.service.AnalysisService;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;
import com.sixteam.ssgame.global.common.dto.BaseResponseDto;
import com.sixteam.ssgame.global.common.util.LogUtil;
import com.sixteam.ssgame.global.error.dto.ErrorStatus;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/analysis")
@RestController
public class AnalysisController {

    private final AnalysisService analysisService;

    @Transactional
    @GetMapping("/graph")
    public BaseResponseDto getGraph(Authentication authentication) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        Integer status = null;
        String msg = null;
        Map<String, Object> data = new HashMap<>();

        System.out.println("!" + authentication);

        if (authentication == null) {
            throw new CustomException("authentication is null", ErrorStatus.UNAUTHORIZED_ACCESS);
        } else {
            CustomUserDetails details = (CustomUserDetails) authentication.getDetails();
            Long memberSeq = details.getMember().getMemberSeq();

            List<RadarChartInfoDto> radarChartInfoDtos = new LinkedList<>();
            for (RadarChartInfo radarChartInfo : analysisService.getGraph(memberSeq)) {
                radarChartInfoDtos.add(RadarChartInfoDto.builder()
                        .subject(radarChartInfo.getCategory().getCategoryName())
                        .categoryRatio(radarChartInfo.getCategoryRatio())
                        .build());

            }
            data.put("categories", radarChartInfoDtos);

            status = HttpStatus.OK.value();
            msg = "그래프 정보 조회 성공";
        }

        return BaseResponseDto.builder()
                .status(status)
                .msg(msg)
                .data(data)
                .build();
    }

    @GetMapping("/genres")
    public BaseResponseDto getMostPlayedGenre(Authentication authentication) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        Integer status = null;
        String msg = null;
        Map<String, Object> data = new HashMap<>();

        System.out.println("!" + authentication);

        if (authentication == null) {
            throw new CustomException("authentication is null", ErrorStatus.UNAUTHORIZED_ACCESS);
        } else {
            CustomUserDetails details = (CustomUserDetails) authentication.getDetails();
            Long memberSeq = details.getMember().getMemberSeq();

            data.put("MostPlayedGenres", analysisService.getMostPlayedGenres(memberSeq));

            status = HttpStatus.OK.value();
            msg = "가장 많이 플레이한 장르 조회 성공";
        }

        return BaseResponseDto.builder()
                .status(status)
                .msg(msg)
                .data(data)
                .build();
    }

}
