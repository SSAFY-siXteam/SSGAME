package com.sixteam.ssgame.api.gameInfo.repository;

import com.sixteam.ssgame.api.gameInfo.entity.GameGenre;
import com.sixteam.ssgame.api.gameInfo.entity.GameGenreSeq;
import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameGenreRepository extends JpaRepository<GameGenre, GameGenreSeq> {

    List<GameGenre> findAllByGameInfo(GameInfo gameInfo);

}
