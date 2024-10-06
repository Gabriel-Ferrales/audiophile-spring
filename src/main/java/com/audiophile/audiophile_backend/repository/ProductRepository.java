package com.audiophile.audiophile_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.audiophile.audiophile_backend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
