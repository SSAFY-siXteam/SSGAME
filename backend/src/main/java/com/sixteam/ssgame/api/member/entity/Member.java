package com.sixteam.ssgame.api.member.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString(of = {"memberSeq", "id", "password", "email", "steamID", "isDeleted", "createdDate"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "tb_member",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id"),
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
    private String id;

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
    public Member(Long memberSeq, String id, String password, String email, String steamID) {
        this.memberSeq = memberSeq;
        this.id = id;
        this.password = password;
        this.email = email;
        this.steamID = steamID;
    }
}
