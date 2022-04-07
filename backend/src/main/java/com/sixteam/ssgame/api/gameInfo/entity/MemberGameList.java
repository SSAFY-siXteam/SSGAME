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
@Table(
        name = "tb_member_game_list",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "member_game_seq",
                        columnNames = {"member_seq", "game_seq"}
                )
        }
)
@Entity
public class MemberGameList {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long memberGameListSeq;

    @JoinColumn(name = "member_seq", nullable = false)
    @ManyToOne(fetch = LAZY)
    private Member member;

    @JoinColumn(name = "game_seq", nullable = false)
    @ManyToOne(fetch = LAZY)
    private GameInfo gameInfo;

    @Column(nullable = false)
    private Long memberPlayTime;

    @Column(nullable = false)
    private Integer memberGameRating;

    @Builder
    public MemberGameList(Long memberGameListSeq, Member member, GameInfo gameInfo, Long memberPlayTime, Integer memberGameRating) {
        this.memberGameListSeq = memberGameListSeq;
        this.member = member;
        this.gameInfo = gameInfo;
        this.memberPlayTime = memberPlayTime;
        this.memberGameRating = memberGameRating;
    }

    public void updateMemberGameRating (Integer memberGameRating){
        this.memberGameRating = memberGameRating;
    }
}
