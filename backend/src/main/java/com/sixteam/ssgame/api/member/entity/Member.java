package com.sixteam.ssgame.api.member.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString(of = {"memberSeq", "ssgameId", "password", "email", "steamID", "isDeleted", "createdDate"})
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

    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private boolean isDeleted;

    @Builder
    public Member(Long memberSeq, String ssgameId, String password, String email, String steamID) {
        this.memberSeq = memberSeq;
        this.ssgameId = ssgameId;
        this.password = password;
        this.email = email;
        this.steamID = steamID;
    }
}
