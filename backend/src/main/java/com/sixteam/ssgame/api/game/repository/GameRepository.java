package com.sixteam.ssgame.api.game.repository;

import com.sixteam.ssgame.api.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game findBySteamAppid(Long steamAppid);

    Game findByGameSeq(Long gameSeq);

}
