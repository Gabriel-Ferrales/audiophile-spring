package com.audiophile.audiophile_backend.dao;

import java.util.List;
import java.util.Optional;
import com.audiophile.audiophile_backend.entity.User;

public interface UserDAO {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void deleteById(Long id);
    boolean existsById(Long id);
    Optional<User> findByEmail(String email);
        // Otros métodos...


}
