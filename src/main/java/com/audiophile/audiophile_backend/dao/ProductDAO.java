package com.audiophile.audiophile_backend.dao;

import com.audiophile.audiophile_backend.entity.Product;
import java.util.List;
import java.util.Optional;


public interface ProductDAO {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
    boolean existsById(Long id);
}
