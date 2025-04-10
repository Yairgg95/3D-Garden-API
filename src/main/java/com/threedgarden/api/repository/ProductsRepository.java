package com.threedgarden.api.repository;

import com.threedgarden.api.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
