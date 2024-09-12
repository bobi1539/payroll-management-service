package com.zero.payroll.management.service.impl;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.dto.JwtComponentDto;
import com.zero.payroll.management.dto.request.LoginRequest;
import com.zero.payroll.management.dto.response.LoginResponse;
import com.zero.payroll.management.entity.MUser;
import com.zero.payroll.management.exception.BusinessException;
import com.zero.payroll.management.repository.UserRepository;
import com.zero.payroll.management.service.AuthService;
import com.zero.payroll.management.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {
        MUser user = getByUsername(request.getUsername());
        verifyPassword(request.getPassword(), user.getPassword());
        return buildLoginResponse(generateToken(user));
    }

    private MUser getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(GlobalMessage.WRONG_USERNAME_OR_PASSWORD));
    }

    private void verifyPassword(String rawPassword, String hashPassword) {
        boolean matches = passwordEncoder.matches(rawPassword, hashPassword);
        if (!matches) {
            throw new BusinessException(GlobalMessage.WRONG_USERNAME_OR_PASSWORD);
        }
    }

    private String generateToken(MUser user) {
        JwtComponentDto dto = JwtComponentDto.builder()
                .username(user.getUsername())
                .build();
        return jwtService.generateToken(dto);
    }

    private LoginResponse buildLoginResponse(String jwt) {
        return LoginResponse.builder().jwt(jwt).build();
    }
}
