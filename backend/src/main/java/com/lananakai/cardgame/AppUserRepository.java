package com.lananakai.cardgame;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// Database access for user accounts.
// Spring builds these queries from the method names (findBy + Username, etc.)
public interface AppUserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findByUsername(String username);

    boolean existsByUsername(String username);
}
