package com.zero.payroll.management.service;

import com.zero.payroll.management.dto.JwtComponentDto;

public interface JwtService {

    String generateToken(JwtComponentDto dto);

    String extractUsername(String token);
}
