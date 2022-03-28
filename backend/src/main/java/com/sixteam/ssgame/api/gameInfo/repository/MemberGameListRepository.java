package com.sixteam.ssgame.api.gameInfo.repository;

import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import com.sixteam.ssgame.api.gameInfo.entity.MemberGameList;
import com.sixteam.ssgame.api.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberGameListRepository extends JpaRepository<MemberGameList, Long> {

    MemberGameList findByMemberAndGameInfo(Member member, GameInfo gameInfo);

}
