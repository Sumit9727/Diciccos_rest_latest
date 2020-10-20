package com.prakat.middleware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prakat.middleware.entity.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer>{
	public Optional<RefreshToken> findByUserId(int userId);
	public Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
