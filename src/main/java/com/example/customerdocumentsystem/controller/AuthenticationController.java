package com.example.customerdocumentsystem.controller;

import com.example.customerdocumentsystem.dto.request.RefreshTokenRequestDTO;
import com.example.customerdocumentsystem.dto.request.UserLoginRequestDTO;
import com.example.customerdocumentsystem.dto.request.UserRegisterRequestDTO;
import com.example.customerdocumentsystem.dto.response.AuthResponseDTO;
import com.example.customerdocumentsystem.model.Token;
import com.example.customerdocumentsystem.model.User;
import com.example.customerdocumentsystem.security.JwtTokenProvider;
import com.example.customerdocumentsystem.service.AuthenticationService;
import com.example.customerdocumentsystem.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userLoginRequestDTO.userName(), userLoginRequestDTO.password());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return jwtToken;
    }

    //user registration
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        authenticationService.userRegister(userRegisterRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        AuthResponseDTO response = new AuthResponseDTO();
        Token token = refreshTokenService.getByUser(refreshTokenRequestDTO.userId());
        if (token.getToken().equals(refreshTokenRequestDTO.refreshToken()) &&
                !refreshTokenService.isRefreshExpired(token)) {

            User user = token.getUser();
            String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
            response.setMessage("token successfully refreshed.");
            response.setAccessToken("Bearer " + jwtToken);
            response.setUserId(user.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("refresh token is not valid.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

    }

}
