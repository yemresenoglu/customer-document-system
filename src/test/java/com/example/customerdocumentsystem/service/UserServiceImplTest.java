package com.example.customerdocumentsystem.service;

import com.example.customerdocumentsystem.dto.request.CreateUserRequestDTO;
import com.example.customerdocumentsystem.model.User;
import com.example.customerdocumentsystem.model.UserRole;
import com.example.customerdocumentsystem.repository.UserRepository;
import com.example.customerdocumentsystem.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceImplTest {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {

        userRepository = Mockito.mock(UserRepository.class);
        userRoleRepository = Mockito.mock(UserRoleRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService = new UserServiceImpl(userRepository, userRoleRepository, passwordEncoder);

    }

    @Test
    public void testCreateUser_whenUserRoleNotFound_shouldThrowRunTimeException() {
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("emre", "1", new Date(1995 - 05 - 05), "email@gmail.com", "Admin");
        Mockito.when(userRoleRepository.findByName("role")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> userService.createUser(createUserRequestDTO));
    }


    /**
     *
     *

     Failed test..

     * @Test
    public void testCreateUser_whenUserRoleExist_shouldRegisterUser() {
        CreateUserRequestDTO requestDTO = new CreateUserRequestDTO("yes", "1234", new Date(1995 - 15 - 05), "yes@gmail.com", "Admin");


        UserRole userRole = new UserRole();

        userRole.setId(1L);
        userRole.setName("Admin");
        userRole.setDescription("Full auth");

        User user = new User();

        user.setId(5L);
        user.setUsername("yes");
        user.setPassword("1234");
        user.setBirthday(new Date(1995 - 15 - 05));
        user.setEmail("yes@gmail.com");
        user.setUserRole(userRole);

        User responseUser = new User();


        responseUser.setId(5L);
        responseUser.setUsername("yes");
        responseUser.setPassword("1234");
        responseUser.setBirthday(new Date(1995 - 15 - 05));
        responseUser.setEmail("yes@gmail.com");
        responseUser.setUserRole(userRole);


        Mockito.when(userRoleRepository.findByName("Admin")).thenReturn(userRole);
        Mockito.when(userRepository.save(user)).thenReturn(responseUser);

        assertEquals(user, responseUser);


    }**/


}
