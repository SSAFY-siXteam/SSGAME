package com.sixteam.ssgame.api.recommendation.entity;

import com.sixteam.ssgame.api.gameInfo.entity.GameInfo;
import com.sixteam.ssgame.api.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "tb_member_recommended_game",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "member_game_seq",
                        columnNames = {"member_seq", "game_seq"}
                )
        }
)
@Entity
public class MemberRecommendedGame {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long recommendedGameSeq;

    @Column(nullable = false)
    private Double recommendedRatio;

    @JoinColumn(name = "member_seq", nullable = false)
    @ManyToOne(fetch = LAZY)
    private Member member;

    @JoinColumn(name = "game_seq", nullable = false)
    @ManyToOne(fetch = LAZY)
    private GameInfo gameInfo;

}
