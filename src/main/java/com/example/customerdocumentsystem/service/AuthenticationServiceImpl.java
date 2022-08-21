package com.example.customerdocumentsystem.service;

import com.example.customerdocumentsystem.dto.request.UserRegisterRequestDTO;
import com.example.customerdocumentsystem.model.User;
import com.example.customerdocumentsystem.repository.UserRepository;
import com.example.customerdocumentsystem.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void userRegister(UserRegisterRequestDTO userRegisterRequestDTO) {

        if (!Objects.isNull(userRepository.findByUsername(userRegisterRequestDTO.username()))) {
            throw new RuntimeException("There is a user by " + userRegisterRequestDTO.username());
        } else {

            User user = new User();

            user.setUsername(userRegisterRequestDTO.username());
            user.setPassword(passwordEncoder.encode(userRegisterRequestDTO.password()));
            user.setBirthday(userRegisterRequestDTO.birthday());
            user.setEmail(userRegisterRequestDTO.email());
            user.setUserRole(userRoleRepository.findByName(userRegisterRequestDTO.userRole()));

            userRepository.save(user);

            log.info("User successfully registered.", HttpStatus.CREATED);
        }

    }
}
