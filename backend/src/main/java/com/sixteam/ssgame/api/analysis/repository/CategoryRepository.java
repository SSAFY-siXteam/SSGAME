package com.sixteam.ssgame.api.analysis.repository;

import com.sixteam.ssgame.api.analysis.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName(String preferredCategory);

}
