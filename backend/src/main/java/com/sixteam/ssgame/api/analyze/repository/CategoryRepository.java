package com.sixteam.ssgame.api.analyze.repository;

import com.sixteam.ssgame.api.analyze.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategorySeq(Long CategorySeq);
}
