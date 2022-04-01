package com.sixteam.ssgame.api.analysis.service;


import com.sixteam.ssgame.api.analysis.dto.MostPlayedGamesDto;
import com.sixteam.ssgame.api.analysis.dto.MostPlayedGenreDto;
import com.sixteam.ssgame.api.analysis.dto.RadarChartInfoDto;

import java.util.List;

public interface AnalysisService {

    List<RadarChartInfoDto> getGraph(Long memberSeq);

    List<MostPlayedGenreDto> getMostPlayedGenres(Long memberSeq);

    List<MostPlayedGamesDto> getMostPlayedGames(Long memberSeq);

}
