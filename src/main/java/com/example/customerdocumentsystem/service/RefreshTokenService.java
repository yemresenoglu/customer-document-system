package com.example.customerdocumentsystem.service;

import com.example.customerdocumentsystem.model.Token;

public interface RefreshTokenService {


    Token getByUser(Long userId);

    boolean isRefreshExpired(Token token);
}
