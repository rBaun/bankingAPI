package com.rbaun.banking.repository.account;

import com.rbaun.banking.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for the account entity
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Find an account by the account number
     * @param accountNumber the account number
     * @return the account matching the account number
     */
    Optional<Account> findByAccountNumber(String accountNumber);

    /**
     * Delete an account by the account number
     * @param accountNumber the account number of the account to delete
     */
    void deleteByAccountNumber(String accountNumber);

    /**
     * Find an account on the customer by the account title
     */
    Optional<Account> findByCustomer_IdAndTitle(Long customerId, String title);
}
