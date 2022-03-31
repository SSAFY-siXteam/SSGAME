package com.sixteam.ssgame.api.analysis.service;

import com.sixteam.ssgame.api.analysis.dto.MostPlayedGamesDto;
import com.sixteam.ssgame.api.analysis.entity.RadarChartInfo;
import com.sixteam.ssgame.api.analysis.repository.RadarChartInfoRepository;
import com.sixteam.ssgame.api.gameInfo.entity.GameGenre;
import com.sixteam.ssgame.api.gameInfo.entity.MemberGameList;
import com.sixteam.ssgame.api.gameInfo.repository.GameGenreRepository;
import com.sixteam.ssgame.api.gameInfo.repository.MemberGameListRepository;
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

    private final MemberGameListRepository memberGameListRepository;

    private final GameGenreRepository gameGenreRepository;

    @Override
    public List<RadarChartInfo> getGraph(Long memberSeq) {

        Member member = memberRepository.findByMemberSeq(memberSeq);
        return radarChartInfoRepository.findByMember(member);
    }

    @Override
    public List<MostPlayedGamesDto> getMostPlayedGames(Long memberSeq) {
        Member member = memberRepository.findByMemberSeq(memberSeq);
//        memberGameListRepository.findMostPlayedGamesByMember(member);
        List<MemberGameList> memberGameLists = memberGameListRepository.findMostPlayedGamesByMember(member);
        List<MostPlayedGamesDto> mostPlayedGamesDtos = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            if (memberGameLists.get(i).getMemberPlayTime() == 0) {
                break;
            }
            List<GameGenre> gameGenres = memberGameLists.get(i).getGameInfo().getGameGenres();
            String[] genres = new String[((gameGenres.size() > 3) ? 3 : gameGenres.size())];
            for (int j = 0; j < ((gameGenres.size() > 3) ? 3 : gameGenres.size()); j++) {
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
