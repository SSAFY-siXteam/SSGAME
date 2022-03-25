package com.sixteam.ssgame.api.game.entity;

import com.sixteam.ssgame.api.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(GameMemberSeq.class)
@Table(name = "tb_member_game_list")
@Entity
public class MemberGameList {

    @JoinColumn(name = "member_seq")
    @ManyToOne(fetch = LAZY)
    @Id
    private Member member;

    @JoinColumn(name = "game_seq")
    @ManyToOne(fetch = LAZY)
    @Id
    private GameInfo gameInfo;

    @Column(nullable = false)
    private Long memberPlayTime;

    private Integer memberGameRating;

    @Builder
    public MemberGameList(Member member, GameInfo gameInfo, Long memberPlayTime, Integer memberGameRating) {
        this.member = member;
        this.gameInfo = gameInfo;
        this.memberPlayTime = memberPlayTime;
        this.memberGameRating = memberGameRating;
    }
}
