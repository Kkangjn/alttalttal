package com.alttalttal.mini_project.repository;

import com.alttalttal.mini_project.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {

    boolean existsByRefreshToken(String token);
}
