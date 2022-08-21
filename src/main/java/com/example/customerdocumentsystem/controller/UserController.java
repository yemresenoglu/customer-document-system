package com.example.customerdocumentsystem.controller;

import com.example.customerdocumentsystem.dto.request.CreateUserRequestDTO;
import com.example.customerdocumentsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {

        userService.createUser(createUserRequestDTO);

        return ResponseEntity.ok().build();
    }
}
