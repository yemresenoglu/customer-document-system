package com.example.customerdocumentsystem.dto.request;

import java.util.Date;

public record UserRegisterRequestDTO(String username,
                                     String password,
                                     Date birthday,
                                     String email,
                                     String userRole) {
}
