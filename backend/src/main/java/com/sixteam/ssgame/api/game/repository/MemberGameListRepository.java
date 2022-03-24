package com.sixteam.ssgame.api.game.repository;

import com.sixteam.ssgame.api.game.entity.MemberGameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberGameListRepository extends JpaRepository<MemberGameList, Long> {
}
