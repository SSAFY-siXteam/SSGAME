package com.sixteam.ssgame.api.member.entity;

import com.sixteam.ssgame.api.analysis.entity.MemberFrequentGenre;
import com.sixteam.ssgame.api.analysis.entity.RadarChartInfo;
import com.sixteam.ssgame.api.gameInfo.entity.MemberGameList;
import com.sixteam.ssgame.api.recommendation.entity.MemberRecommendedGame;
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


    @OneToMany(mappedBy = "member", cascade = ALL)
    private final List<MemberPreferredCategory> memberPreferredCategories = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = ALL)
    private final List<MemberGameList> memberGameLists = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = ALL)
    private final List<RadarChartInfo> radarChartInfos = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = ALL)
    private final List<MemberFrequentGenre> memberFrequentGenres = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = ALL)
    private final List<MemberRecommendedGame> memberRecommendedGames = new ArrayList<>();
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
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;
    @Column(nullable = false)
    private Boolean isDeleted;

    @Builder
    public Member(Long memberSeq, String ssgameId, String password, String email, String steamID, String steamNickname, LocalDateTime createdDate, String avatarUrl, boolean isPublic, Long gameCount, boolean isDeleted) {
        this.memberSeq = memberSeq;
        this.ssgameId = ssgameId;
        this.password = password;
        this.email = email;
        this.steamID = steamID;
        this.steamNickname = steamNickname;
        this.avatarUrl = avatarUrl;
        this.isPublic = isPublic;
        this.gameCount = gameCount;
        this.isDeleted = isDeleted;
        this.createdDate = LocalDateTime.now();
    }

    public void changeMember(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
