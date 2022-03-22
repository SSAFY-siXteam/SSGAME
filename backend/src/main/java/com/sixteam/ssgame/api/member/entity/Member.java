package com.sixteam.ssgame.api.member.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@ToString(of = {"memberSeq", "ssgameId", "password", "email", "steamID", "steamNickname", "avartarUrl", "isDeleted", "createdDate"})
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

    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long memberSeq;

    @Column(nullable = false)
    private String ssgameId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(name = "steam_id", nullable = false)
    private String steamID;

    @Column(nullable = false)
    private String steamNickname;

    @Column(nullable = false)
    private String avartarUrl;

    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private boolean isDeleted;

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<MemberPreferredCategory> memberPreferredCategories = new ArrayList<>();

    @Builder
    public Member(Long memberSeq, String ssgameId, String password, String email, String steamID, String steamNickname, String avartarUrl) {
        this.memberSeq = memberSeq;
        this.ssgameId = ssgameId;
        this.password = password;
        this.email = email;
        this.steamID = steamID;
        this.steamNickname = steamNickname;
        this.avartarUrl = avartarUrl;
    }
}
