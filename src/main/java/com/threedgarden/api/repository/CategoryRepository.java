package com.threedgarden.api.repository;

import com.threedgarden.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    long deleteByIdNotNullAllIgnoreCase();

    Optional<Category> findByName(String name);
}
