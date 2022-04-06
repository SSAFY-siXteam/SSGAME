package com.sixteam.ssgame.api.gameInfo.entity;

import com.sixteam.ssgame.api.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_member_game_list")
@Entity
public class MemberGameList {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long memberGameListSeq;

    @JoinColumn(name = "member_seq")
    @ManyToOne(fetch = LAZY)
    private Member member;

    @JoinColumn(name = "game_seq")
    @ManyToOne(fetch = LAZY)
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

    public void updateMemberGameRating (Integer memberGameRating){
        this.memberGameRating = memberGameRating;
    }
}
