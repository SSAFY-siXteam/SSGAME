package com.sixteam.ssgame.api.recommendation.repository;

import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.recommendation.entity.MemberRecommendedGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRecommendedGameRepository extends JpaRepository<MemberRecommendedGame, Long> {
<<<<<<< HEAD
=======

    void deleteByMember(Member member);

>>>>>>> 96959a4f3f9bd7bab214fd0ce56f0f6f546d481d
}
