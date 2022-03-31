package com.sixteam.ssgame.api.recommendation.repository;

import com.sixteam.ssgame.api.recommendation.entity.MemberRecommendedGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRecommendedGameRepository extends JpaRepository<MemberRecommendedGame, Long> {
}
