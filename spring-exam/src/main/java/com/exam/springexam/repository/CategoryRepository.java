package com.exam.springexam.repository;

import com.exam.springexam.model.entity.Category;
import com.exam.springexam.model.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    Optional<Category> findByCategoryName(CategoryName categoryName);
}
