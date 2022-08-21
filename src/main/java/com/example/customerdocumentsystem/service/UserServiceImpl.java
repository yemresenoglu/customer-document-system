package com.example.customerdocumentsystem.service;

import com.example.customerdocumentsystem.dto.request.CreateUserRequestDTO;
import com.example.customerdocumentsystem.model.User;
import com.example.customerdocumentsystem.repository.UserRepository;
import com.example.customerdocumentsystem.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void createUser(CreateUserRequestDTO createUserRequestDTO) {

        if (Objects.isNull(userRoleRepository.findByName(createUserRequestDTO.userRole()))) {
            throw new RuntimeException("User role not found!");
        }

        User user = new User();

        user.setUsername(createUserRequestDTO.username());
        user.setPassword(passwordEncoder.encode(createUserRequestDTO.password()));
        user.setBirthday(createUserRequestDTO.birthday());
        user.setEmail(createUserRequestDTO.email());
        user.setUserRole(userRoleRepository.findByName(createUserRequestDTO.userRole()));

        userRepository.save(user);
    }


}
