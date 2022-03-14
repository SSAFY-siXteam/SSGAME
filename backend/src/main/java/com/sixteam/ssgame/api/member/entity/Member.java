package com.sixteam.ssgame.api.member.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
//@ToString(of = {"seq", "email", "password", "name", "nickname", "exp", "point", "role", "isDeleted"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(
//        name = "tb_member",
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = "email"),
//                @UniqueConstraint(columnNames = "nickname"),
//                @UniqueConstraint(columnNames = "phoneNumber")
//        }
//)
@Entity
public class Member {

    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue
    @Id
    private Long memberSeq;

}
