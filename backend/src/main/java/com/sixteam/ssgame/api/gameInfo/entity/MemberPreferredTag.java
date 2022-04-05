package com.sixteam.ssgame.api.gameInfo.entity;

import com.sixteam.ssgame.api.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "tb_member_preferred_tag",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "member_tag_seq",
                        columnNames = {"member_seq", "tag_seq"}
                )
        }
)
@Entity
public class MemberPreferredTag {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long memberTagSeq;

    @Column(nullable = false)
    private Double preferredTagRatio;

    @JoinColumn(name = "member_seq", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "tag_seq", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    @Builder
    public MemberPreferredTag(Member member, Tag tag, Double preferredTagRatio) {
        this.member = member;
        this.tag = tag;
        this.preferredTagRatio = preferredTagRatio;
    }
}
