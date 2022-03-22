package com.sixteam.ssgame.api.member.repository;

import com.sixteam.ssgame.api.member.entity.MemberPreferredCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberPreferredCategoryRepository extends JpaRepository<MemberPreferredCategory, Long> {

    List<MemberPreferredCategory> findMemberPreferredCategoriesByMemberMemberSeq(Long memberSeq);
}
