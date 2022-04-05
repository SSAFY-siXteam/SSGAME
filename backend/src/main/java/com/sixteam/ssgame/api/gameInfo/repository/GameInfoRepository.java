package com.sixteam.ssgame.api.gameInfo.repository;

import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameInfoRepository extends JpaRepository<GameInfo, Long> {

    Optional<GameInfo> findBySteamAppid(Long steamAppid);

    Optional<GameInfo> findByGameSeq(Long gameSeq);

}
