package com.sixteam.ssgame.api.analysis.repository;

import com.sixteam.ssgame.api.analysis.entity.RadarChartInfo;
import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.entity.MemberCategorySeq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RadarChartInfoRepository extends JpaRepository<RadarChartInfo, MemberCategorySeq> {

    void deleteByMember(Member member);

    List<RadarChartInfo> findByMember(Member member);
}
