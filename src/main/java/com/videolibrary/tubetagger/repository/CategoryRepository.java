package com.videolibrary.tubetagger.repository;

import com.videolibrary.tubetagger.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
