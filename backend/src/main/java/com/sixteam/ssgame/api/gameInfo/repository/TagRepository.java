package com.sixteam.ssgame.api.gameInfo.repository;

import com.sixteam.ssgame.api.gameInfo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {

}
