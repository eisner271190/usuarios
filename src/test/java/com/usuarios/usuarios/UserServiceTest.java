/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios;

import com.usuarios.usuarios.application.service.UserService;
import com.usuarios.usuarios.domain.constants.UserConstants;
import com.usuarios.usuarios.domain.model.RolModel;
import com.usuarios.usuarios.domain.model.UserModel;
import com.usuarios.usuarios.domain.repositories.IRolRepository;
import com.usuarios.usuarios.domain.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IRolRepository rolRepository;

    @Mock
    private PasswordEncoder bCryptPasswordEncoder;

    private UserService userService;

    private UserModel user;
    private RolModel role;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, rolRepository, bCryptPasswordEncoder);
        user = new UserModel();
        user.setId(1L);
        user.setClave("password");
        user.setFecha_nacimiento(new Date(2000 - 1900, Calendar.JANUARY, 1)); // Date deprecated but used for simplicity

        role = new RolModel();
        role.setId(1L);
        role.setNombre(UserConstants.ROLE_OWNER);
    }

    @Test
    void getOwnerById_userExistsAndIsOwner_returnsTrue() {
        user.setId_rol(role);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(rolRepository.findByNombre(UserConstants.ROLE_OWNER)).thenReturn(Optional.of(role));

        boolean result = userService.getOwnerById(user.getId());

        assertTrue(result);
    }

    @Test
    void getOwnerById_userDoesNotExist_returnsFalse() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        boolean result = userService.getOwnerById(user.getId());

        assertFalse(result);
    }

    @Test
    void saveAccountOwner_validUser_savesUser() throws Exception {
        when(rolRepository.findByNombre(UserConstants.ROLE_OWNER)).thenReturn(Optional.of(role));
        when(bCryptPasswordEncoder.encode(any())).thenReturn("encodedPassword");

        userService.saveAccountOwner(user);

        verify(userRepository).save(any(UserModel.class));
    }

    @Test
    void saveAccountOwner_invalidUser_throwsException() {
        user.setFecha_nacimiento(new Date(2010 - 1900, Calendar.JANUARY, 1)); // Not an adult

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.saveAccountOwner(user);
        });

        assertEquals("{validation.date.isadult}", exception.getMessage());
    }

    @Test
    void saveAccount_validRole_savesUser() throws Exception {
        when(rolRepository.findByNombre(UserConstants.ROLE_OWNER)).thenReturn(Optional.of(role));
        when(bCryptPasswordEncoder.encode(any())).thenReturn("encodedPassword");

        userService.saveAccount(user, UserConstants.ROLE_OWNER);

        verify(userRepository).save(any(UserModel.class));
    }

    @Test
    void saveAccount_invalidRole_throwsException() {
        when(rolRepository.findByNombre(UserConstants.ROLE_OWNER)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            userService.saveAccount(user, UserConstants.ROLE_OWNER);
        });

        assertEquals("{validation.saveAccount.roleNotExist}", exception.getMessage());
    }
}