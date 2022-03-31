package com.sixteam.ssgame.api.analysis.repository;

import com.sixteam.ssgame.api.analysis.entity.MemberFrequentGenre;
import com.sixteam.ssgame.api.analysis.entity.MemberGenreSeq;
import com.sixteam.ssgame.api.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFrequentGenreRepository extends JpaRepository<MemberFrequentGenre, MemberGenreSeq> {

    void deleteByMember(Member member);

}
