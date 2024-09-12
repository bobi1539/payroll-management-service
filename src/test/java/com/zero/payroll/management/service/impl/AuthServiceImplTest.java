package com.zero.payroll.management.service.impl;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.dto.request.LoginRequest;
import com.zero.payroll.management.dto.response.LoginResponse;
import com.zero.payroll.management.entity.MUser;
import com.zero.payroll.management.exception.BusinessException;
import com.zero.payroll.management.helper.ObjectDummy;
import com.zero.payroll.management.repository.UserRepository;
import com.zero.payroll.management.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    private final LoginRequest loginRequest = ObjectDummy.getLoginRequest();
    private final MUser user = ObjectDummy.getUser();
    private static final String USERNAME = "admin";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_Success() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtService.generateToken(any())).thenReturn(ObjectDummy.JWT);

        LoginResponse response = authService.login(loginRequest);
        assertEquals(ObjectDummy.JWT, response.getJwt());

        verify(userRepository, times(1)).findByUsername(USERNAME);
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
        verify(jwtService, times(1)).generateToken(any());
    }

    @Test
    void testLogin_UsernameNotFound() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.empty());

        BusinessException e = assertThrows(BusinessException.class, () -> authService.login(loginRequest));
        assertEquals(GlobalMessage.WRONG_USERNAME_OR_PASSWORD.status, e.getStatus());
        assertEquals(GlobalMessage.WRONG_USERNAME_OR_PASSWORD.message, e.getMessage());

        verify(userRepository, times(1)).findByUsername(USERNAME);
    }

    @Test
    void testLogin_PasswordNotMatch() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        BusinessException e = assertThrows(BusinessException.class, () -> authService.login(loginRequest));
        assertEquals(GlobalMessage.WRONG_USERNAME_OR_PASSWORD.status, e.getStatus());
        assertEquals(GlobalMessage.WRONG_USERNAME_OR_PASSWORD.message, e.getMessage());

        verify(userRepository, times(1)).findByUsername(USERNAME);
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
    }
}