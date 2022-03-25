package com.sixteam.ssgame.api.analyze.entity;

import com.sixteam.ssgame.api.game.entity.Genre;
import com.sixteam.ssgame.api.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(MemberGenreSeq.class)
@Table(name = "tb_member_frequent_genre")
@Entity
public class MemberFrequentGenre {

    @JoinColumn(name = "member_seq")
    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    private Member member;

    @JoinColumn(name = "genre_seq")
    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    private Genre genre;

    private Long genreCount;

}
