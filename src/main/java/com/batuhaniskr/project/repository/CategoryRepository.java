package com.batuhaniskr.project.repository;

import com.batuhaniskr.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByCategoryName(String name);
}
