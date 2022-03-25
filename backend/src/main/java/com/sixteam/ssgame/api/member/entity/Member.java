package com.sixteam.ssgame.api.member.entity;

import com.sixteam.ssgame.api.analyze.entity.MemberFrequentGenre;
import com.sixteam.ssgame.api.analyze.entity.RadarChartInfo;
import com.sixteam.ssgame.api.game.entity.MemberGameList;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@ToString(of = {"memberSeq", "ssgameId", "password", "email", "steamID", "steamNickname", "avatarUrl", "isPublic", "gameCount", "isDeleted", "createdDate"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "tb_member",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "ssgameId"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "steam_id")
        }
)
@Entity
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long memberSeq;

    @Column(nullable = false, unique = true)
    private String ssgameId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "steam_id", nullable = false, unique = true)
    private String steamID;

    @Column(nullable = false)
    private String steamNickname;

    @Column(nullable = false)
    private String avatarUrl;

    @Column(nullable = false)
    private Boolean isPublic;

    @Column(nullable = false)
    private Long gameCount;

    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<MemberPreferredCategory> memberPreferredCategories = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<MemberGameList> memberGameLists = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<RadarChartInfo> radarChartInfos = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<MemberFrequentGenre> memberFrequentGenres = new ArrayList<>();


    @Builder
    public Member(Long memberSeq, String ssgameId, String password, String email, String steamID, String steamNickname, String avatarUrl, boolean isPublic, Long gameCount) {
        this.memberSeq = memberSeq;
        this.ssgameId = ssgameId;
        this.password = password;
        this.email = email;
        this.steamID = steamID;
        this.steamNickname = steamNickname;
        this.avatarUrl = avatarUrl;
        this.isPublic = isPublic;
        this.gameCount = gameCount;
    }
}
