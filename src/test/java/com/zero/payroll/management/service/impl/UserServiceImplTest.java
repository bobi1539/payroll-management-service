package com.zero.payroll.management.service.impl;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.entity.MUser;
import com.zero.payroll.management.exception.BusinessException;
import com.zero.payroll.management.helper.ObjectDummy;
import com.zero.payroll.management.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private final MUser user = ObjectDummy.getUser();
    private static final String USERNAME = "admin";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadByUsername_Success() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(USERNAME);
        assertEquals(user.getUsername(), userDetails.getUsername());

        verify(userRepository, times(1)).findByUsername(USERNAME);
    }

    @Test
    void testLoadByUsername_NotFound() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.empty());

        BusinessException e = assertThrows(BusinessException.class, () -> userService.loadUserByUsername(USERNAME));
        assertEquals(GlobalMessage.DATA_NOT_FOUND.status, e.getStatus());
        assertEquals(GlobalMessage.DATA_NOT_FOUND.message, e.getMessage());

        verify(userRepository, times(1)).findByUsername(USERNAME);
    }
}