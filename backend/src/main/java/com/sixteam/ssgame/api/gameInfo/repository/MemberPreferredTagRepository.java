package com.sixteam.ssgame.api.gameInfo.repository;

import com.sixteam.ssgame.api.gameInfo.entity.MemberPreferredTag;
import com.sixteam.ssgame.api.gameInfo.entity.Tag;
import com.sixteam.ssgame.api.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberPreferredTagRepository extends JpaRepository<MemberPreferredTag, Long> {

    void deleteByMember(Member member);

    MemberPreferredTag findByMemberAndTag(Member member, Tag tag);

}
