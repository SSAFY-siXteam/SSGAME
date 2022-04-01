package com.sixteam.ssgame.api.gameInfo.repository;

import com.sixteam.ssgame.api.gameInfo.dto.AverageRatingDto;
import com.sixteam.ssgame.api.gameInfo.entity.MemberGameList;
import com.sixteam.ssgame.api.member.dto.MemberGameDto;
import com.sixteam.ssgame.api.member.entity.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberGameListRepositoryCustom {

    AverageRatingDto getAverageRatingByGameSeq(Long gameSeq);

    Integer countMemberGameListByFilterAndSearch(Long memberSeq, boolean filter, String search);

    List<MemberGameDto> findMemberGameDtos(Long memberSeq, Pageable pageable, boolean filter, String search);

    List<MemberGameList> findMostPlayedGamesByMember(Member member);

}
