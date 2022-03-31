package com.sixteam.ssgame.api.analysis.repository;

import com.sixteam.ssgame.api.analysis.entity.RadarChartInfo;
import com.sixteam.ssgame.api.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RadarChartInfoRepository extends JpaRepository<RadarChartInfo, Long> {

    void deleteAllByMember(Member member);

}
