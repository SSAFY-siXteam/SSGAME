package com.sixteam.ssgame.api.game.repository;

import com.sixteam.ssgame.api.game.entity.Game;
import com.sixteam.ssgame.api.game.entity.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameGenreRepository extends JpaRepository<GameGenre, Long> {

    List<GameGenre> findAllByGame(Game game);

}
