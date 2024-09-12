package com.zero.payroll.management.service;

import com.zero.payroll.management.dto.request.LoginRequest;
import com.zero.payroll.management.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}
