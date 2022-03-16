package com.sixteam.ssgame.api.member.repository;

import com.sixteam.ssgame.api.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsBySsgameId(String ssgameId);

    boolean existsByEmail(String email);

    boolean existsBySteamID(String steamID);
}
