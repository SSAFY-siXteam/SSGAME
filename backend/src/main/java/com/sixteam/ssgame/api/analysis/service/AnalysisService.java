package com.sixteam.ssgame.api.analysis.service;

import com.sixteam.ssgame.api.analysis.entity.RadarChartInfo;

import java.util.List;

public interface AnalysisService {


    List<RadarChartInfo> getGraph(Long memberSeq);

}
