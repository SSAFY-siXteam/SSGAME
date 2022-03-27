package com.sixteam.ssgame.api.game.repository;

import com.sixteam.ssgame.api.game.entity.Game;
import com.sixteam.ssgame.api.game.entity.MemberGameList;
import com.sixteam.ssgame.api.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberGameListRepository extends JpaRepository<MemberGameList, Long> {

    MemberGameList findByMemberAndGame(Member member, Game game);

}
