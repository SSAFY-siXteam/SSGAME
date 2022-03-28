package com.sixteam.ssgame.api.analysis.entity;

import com.sixteam.ssgame.api.gameInfo.entity.Genre;
import com.sixteam.ssgame.api.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(MemberGenreSeq.class)
@Table(name = "tb_member_frequent_genre")
@Entity
public class MemberFrequentGenre {

    @JoinColumn(name = "member_seq")
    @ManyToOne(fetch = LAZY)
    @Id
    private Member member;

    @JoinColumn(name = "genre_seq")
    @ManyToOne(fetch = LAZY)
    @Id
    private Genre genre;

    @Column(nullable = false)
    private Long genreCount;

}
