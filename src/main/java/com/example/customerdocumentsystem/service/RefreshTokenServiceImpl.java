package com.example.customerdocumentsystem.service;

import com.example.customerdocumentsystem.model.Token;
import com.example.customerdocumentsystem.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${refresh.token.expires.in}")
    Long expireSeconds;
    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    public Token getByUser(Long userId) {
        return refreshTokenRepository.findByUserId(userId);
    }

    @Override
    public boolean isRefreshExpired(Token token) {
        return token.getExpiryDate().before(new Date());
    }

}
