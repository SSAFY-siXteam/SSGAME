package com.sixteam.ssgame.api.analysis.service;

import com.sixteam.ssgame.api.analysis.dto.MostPlayedGamesDto;
import com.sixteam.ssgame.api.analysis.dto.MostPlayedGenreDto;
import com.sixteam.ssgame.api.analysis.dto.RadarChartInfoDto;
import com.sixteam.ssgame.api.analysis.entity.MemberFrequentGenre;
import com.sixteam.ssgame.api.analysis.entity.RadarChartInfo;
import com.sixteam.ssgame.api.analysis.repository.MemberFrequentGenreRepository;
import com.sixteam.ssgame.api.analysis.repository.RadarChartInfoRepository;
import com.sixteam.ssgame.api.gameInfo.entity.GameGenre;
import com.sixteam.ssgame.api.gameInfo.entity.MemberGameList;
import com.sixteam.ssgame.api.gameInfo.repository.MemberGameListRepository;
import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
import com.sixteam.ssgame.global.common.util.LogUtil;
import com.sixteam.ssgame.global.error.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

import static com.sixteam.ssgame.global.error.dto.ErrorStatus.*;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class AnalysisServiceImpl implements AnalysisService {

    private final RadarChartInfoRepository radarChartInfoRepository;

    private final MemberRepository memberRepository;

    private final MemberFrequentGenreRepository memberFrequentGenreRepository;

    private final MemberGameListRepository memberGameListRepository;

    @Override
    public List<RadarChartInfoDto> getGraph(Long memberSeq) {

        Member member = memberRepository.findByMemberSeq(memberSeq)
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), MEMBER_NOT_FOUND));

        if (member.getGameCount() == 0) {
            throw new CustomException(LogUtil.getElement(), NO_GAME_PLAYED);
        }

        List<RadarChartInfoDto> radarChartInfoDtos = new LinkedList<>();
        for (RadarChartInfo radarChartInfo : radarChartInfoRepository.findByMember(member)) {
            if (radarChartInfo.getCategory().getCategoryName().equals("none")) {
                continue;
            }
            radarChartInfoDtos.add(RadarChartInfoDto.builder()
                    .subject(radarChartInfo.getCategory().getCategoryName())
                    .categoryRatio(radarChartInfo.getCategoryRatio())
                    .build());
        }

        return radarChartInfoDtos;
    }

    @Override
    public List<MostPlayedGenreDto> getMostPlayedGenres(Long memberSeq) {

        Member member = memberRepository.findByMemberSeq(memberSeq)
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), MEMBER_NOT_FOUND));

        List<MemberFrequentGenre> mostPlayedGenres = memberFrequentGenreRepository.findMostPlayedGenresByMember(member);

        List<MostPlayedGenreDto> mostPlayedGenreDtos = new LinkedList<>();
        long sum = 0;
        for (MemberFrequentGenre memberFrequentGenre : mostPlayedGenres) {
            sum += memberFrequentGenre.getGenreCount();
        }

        for (int i = 0; i < 5; i++) {
            if (mostPlayedGenres.get(i).getGenreCount() == 0) {
                break;
            }
            mostPlayedGenreDtos.add(MostPlayedGenreDto.builder()
                    .genre(mostPlayedGenres.get(i)
                            .getGenre()
                            .getGenreName())
                    .ratio(mostPlayedGenres.get(i)
                            .getGenreCount() * 100 / sum)
                    .build());
        }

        return mostPlayedGenreDtos;
    }

    public List<MostPlayedGamesDto> getMostPlayedGames(Long memberSeq) {

        Member member = memberRepository.findByMemberSeq(memberSeq)
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), MEMBER_NOT_FOUND));

        List<MemberGameList> memberGameLists = memberGameListRepository.findMostPlayedGamesByMember(member);
        List<MostPlayedGamesDto> mostPlayedGamesDtos = new LinkedList<>();

        for (int i = 0; i < Math.min(memberGameLists.size(),5); i++) {
            if (memberGameLists.get(i).getMemberPlayTime() == 0) {
                break;
            }
            List<GameGenre> gameGenres = memberGameLists.get(i).getGameInfo().getGameGenres();
            String[] genres = new String[Math.min(gameGenres.size(), 3)];
            for (int j = 0; j < Math.min(gameGenres.size(), 3); j++) {
                genres[j] = gameGenres.get(j).getGenre().getGenreNameKr();
            }
            mostPlayedGamesDtos.add(MostPlayedGamesDto.builder()
                    .gameSeq(memberGameLists.get(i)
                            .getGameInfo()
                            .getGameSeq())
                    .gameName(memberGameLists.get(i)
                            .getGameInfo()
                            .getGameName())
                    .headerImage(memberGameLists.get(i)
                            .getGameInfo()
                            .getHeaderImage())
                    .genres(genres)
                    .build());
        }
        return mostPlayedGamesDtos;
    }
}