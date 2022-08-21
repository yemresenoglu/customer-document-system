package com.example.customerdocumentsystem.service;

import com.example.customerdocumentsystem.dto.request.UserRegisterRequestDTO;

public interface AuthenticationService {
    void userRegister(UserRegisterRequestDTO userRegisterRequestDTO);
}
