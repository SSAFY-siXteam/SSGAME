package com.sixteam.ssgame.api.analysis.service;

import com.sixteam.ssgame.api.analysis.entity.RadarChartInfo;
import com.sixteam.ssgame.api.analysis.repository.RadarChartInfoRepository;
import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class AnalysisServiceImpl implements AnalysisService{

    private final RadarChartInfoRepository radarChartInfoRepository;

    private final MemberRepository memberRepository;

    @Override
    public List<RadarChartInfo> getGraph(Long memberSeq) {

        Member member = memberRepository.findByMemberSeq(memberSeq);
        return radarChartInfoRepository.findByMember(member);
    }
}
