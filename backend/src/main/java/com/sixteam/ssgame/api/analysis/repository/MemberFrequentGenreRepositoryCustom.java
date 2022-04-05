package com.sixteam.ssgame.api.analysis.repository;

import com.sixteam.ssgame.api.analysis.entity.MemberFrequentGenre;
import com.sixteam.ssgame.api.member.entity.Member;

import java.util.List;

public interface MemberFrequentGenreRepositoryCustom {

    List<MemberFrequentGenre> findMostPlayedGenresByMember(Member member);

}
