package com.sixteam.ssgame.api.recommendation.repository;

import com.sixteam.ssgame.api.recommendation.entity.MemberRecommendedGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendedGameRepository extends JpaRepository<MemberRecommendedGame, Long> {
}
