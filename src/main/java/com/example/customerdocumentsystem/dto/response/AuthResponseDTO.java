package com.example.customerdocumentsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDTO {

    private String message;
    private Long userId;
    private String accessToken;
    private String refreshToken;

}
