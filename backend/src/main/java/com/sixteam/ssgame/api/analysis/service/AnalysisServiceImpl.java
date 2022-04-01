package com.sixteam.ssgame.api.analysis.service;

import com.sixteam.ssgame.api.analysis.dto.MostPlayedGenreDto;
import com.sixteam.ssgame.api.analysis.entity.MemberFrequentGenre;
import com.sixteam.ssgame.api.analysis.entity.RadarChartInfo;
import com.sixteam.ssgame.api.analysis.repository.MemberFrequentGenreRepository;
import com.sixteam.ssgame.api.analysis.repository.RadarChartInfoRepository;
import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class AnalysisServiceImpl implements AnalysisService {

    private final RadarChartInfoRepository radarChartInfoRepository;

    private final MemberRepository memberRepository;

    private final MemberFrequentGenreRepository memberFrequentGenreRepository;

    @Override
    public List<RadarChartInfo> getGraph(Long memberSeq) {

        Member member = memberRepository.findByMemberSeq(memberSeq);
        return radarChartInfoRepository.findByMember(member);
    }

    @Override
    public List<MostPlayedGenreDto> getMostPlayedGenres(Long memberSeq) {

        Member member = memberRepository.findByMemberSeq(memberSeq);
        System.out.println(member);
        System.out.println("!");
        List<MemberFrequentGenre> mostPlayedGenres = memberFrequentGenreRepository.findMostPlayedGenresByMember(member);
        System.out.println("@");
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
}
