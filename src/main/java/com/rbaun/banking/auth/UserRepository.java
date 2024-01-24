package com.rbaun.banking.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
}
