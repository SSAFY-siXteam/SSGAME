package com.sixteam.ssgame.api.gameInfo.repository;

import com.sixteam.ssgame.api.gameInfo.entity.MemberPreferredTag;
import com.sixteam.ssgame.api.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPreferredTagRepository extends JpaRepository<MemberPreferredTag, Long> {

    void deleteByMember(Member member);
}
