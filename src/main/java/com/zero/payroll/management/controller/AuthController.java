package com.zero.payroll.management.controller;

import com.zero.payroll.management.constant.Endpoint;
import com.zero.payroll.management.dto.request.LoginRequest;
import com.zero.payroll.management.dto.response.LoginResponse;
import com.zero.payroll.management.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.AUTH)
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }
}
