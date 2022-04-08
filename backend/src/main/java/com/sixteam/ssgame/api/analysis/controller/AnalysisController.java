package com.sixteam.ssgame.api.analysis.controller;

import com.opencsv.CSVReader;
import com.sixteam.ssgame.api.analysis.service.AnalysisService;
import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import com.sixteam.ssgame.api.gameInfo.repository.GameInfoRepository;
import com.sixteam.ssgame.api.member.service.MemberService;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;
import com.sixteam.ssgame.global.common.dto.BaseResponseDto;
import com.sixteam.ssgame.global.common.util.LogUtil;
import com.sixteam.ssgame.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.sixteam.ssgame.global.error.dto.ErrorStatus.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/analysis")
@RestController
public class AnalysisController {

    private final AnalysisService analysisService;

    private final MemberService memberService;

    private final GameInfoRepository gameInfoRepository;



    @GetMapping("/testss")
    public void testss() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("C:\\SSAFY\\2학기\\특화\\db\\번역\\gameInfo_trans.csv")); // 1
        String[] nextLine;
        reader.readNext();
        while ((nextLine = reader.readNext()) != null) {   // 2
            GameInfo gameInfo = gameInfoRepository.findByGameSeq(Long.parseLong(nextLine[0])).get();

            gameInfo.setShortDescriptionKr(nextLine[14]);
            gameInfo.setShortDescription(nextLine[13]);

            gameInfoRepository.save(gameInfo);
        }
    }

    @Transactional
    @GetMapping("/graph")
    public BaseResponseDto getGraph(Authentication authentication) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        if (authentication == null) {
            throw new CustomException(LogUtil.getElement(), UNAUTHORIZED_ACCESS);
        }

        CustomUserDetails details = (CustomUserDetails) authentication.getDetails();

        Map<String, Object> data = new HashMap<>();
        data.put("userNickName", memberService.findMemberBySsgameId(details.getUsername())
                                                                        .getSteamNickname());
        data.put("categories", analysisService.getGraph(details.getMember().getMemberSeq()));
        return BaseResponseDto.builder()
                .status(OK.value())
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
                .status(OK.value())
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
                .status(OK.value())
                .msg("가장 많이 플레이한 게임 조회 성공")
                .data(new HashMap<>() {{
                    put("mostPlayedGames", analysisService.getMostPlayedGames(details.getMember().getMemberSeq()));
                }})
                .build();
    }

    @GetMapping("/calc")
    public BaseResponseDto getCalcMemberPreferred(Authentication authentication) {
        log.info("Called API: {}", LogUtil.getClassAndMethodName());

        if (authentication == null) {
            throw new CustomException(LogUtil.getElement(), UNAUTHORIZED_ACCESS);
        }

        CustomUserDetails details = (CustomUserDetails) authentication.getDetails();
        memberService.calcMemberPreferred(details);

        return BaseResponseDto.builder()
                .status(OK.value())
                .msg("선호도 계산 성공")
                .build();
    }
}
