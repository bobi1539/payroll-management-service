package com.zero.payroll.management.service.impl;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.exception.BusinessException;
import com.zero.payroll.management.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(GlobalMessage.DATA_NOT_FOUND));
    }
}
