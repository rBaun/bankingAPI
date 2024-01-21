package com.rbaun.banking.repository.account;

import com.rbaun.banking.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    void deleteByAccountNumber(String accountNumber);
}
