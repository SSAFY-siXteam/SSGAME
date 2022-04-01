package com.sixteam.ssgame.api.gameInfo.repository;

import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import com.sixteam.ssgame.api.gameInfo.entity.GameTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameTagRepository extends JpaRepository<GameTag, Long> {

    List<GameTag> getByGameInfo(GameInfo gameInfo);

}
