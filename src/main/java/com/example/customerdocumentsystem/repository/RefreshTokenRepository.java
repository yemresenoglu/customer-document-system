package com.example.customerdocumentsystem.repository;

import com.example.customerdocumentsystem.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<Token, Long> {

    Token findByUserId(Long id);
}
