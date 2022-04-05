package com.sixteam.ssgame.api.gameInfo.repository;

import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import com.sixteam.ssgame.api.gameInfo.entity.GameMemberSeq;
import com.sixteam.ssgame.api.gameInfo.entity.MemberGameList;
import com.sixteam.ssgame.api.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberGameListRepository extends JpaRepository<MemberGameList, GameMemberSeq>, MemberGameListRepositoryCustom {

    MemberGameList findByMemberAndGameInfo(Member member, GameInfo gameInfo);

    void deleteByMember(Member member);

    List<MemberGameList> findByMember(Member member);

    boolean existsByMemberAndGameInfo(Member member, GameInfo gameInfo);

}
