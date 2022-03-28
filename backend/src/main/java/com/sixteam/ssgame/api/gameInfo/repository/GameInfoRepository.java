package com.sixteam.ssgame.api.gameInfo.repository;

import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameInfoRepository extends JpaRepository<GameInfo, Long> {

    GameInfo findBySteamAppid(Long steamAppid);

    GameInfo findByGameSeq(Long gameSeq);

}
