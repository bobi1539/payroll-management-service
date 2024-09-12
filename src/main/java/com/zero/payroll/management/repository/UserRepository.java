package com.zero.payroll.management.repository;

import com.zero.payroll.management.entity.MUser;

import java.util.Optional;

public interface UserRepository extends BaseRepository<MUser, Long> {

    Optional<MUser> findByUsername(String username);
}
