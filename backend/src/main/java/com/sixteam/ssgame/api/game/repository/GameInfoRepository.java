package com.sixteam.ssgame.api.game.repository;

import com.sixteam.ssgame.api.game.entity.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameInfoRepository extends JpaRepository<GameInfo, Long> {

    GameInfo findBySteamAppid(String steamAppid);

}
