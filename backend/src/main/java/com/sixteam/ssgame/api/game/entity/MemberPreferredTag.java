package com.sixteam.ssgame.api.game.entity;

import com.sixteam.ssgame.api.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_member_pregerred_tag")
@Entity
public class MemberPreferredTag {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long memberTagSeq;

    @Column(nullable = false)
    private Double preferredTagRatio;

    @JoinColumn(name = "member_seq")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "tag_seq")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

}
