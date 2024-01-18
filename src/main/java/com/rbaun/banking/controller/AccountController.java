package com.rbaun.banking.controller;

import com.rbaun.banking.dto.AccountDTO;
import com.rbaun.banking.model.Account;
import com.rbaun.banking.service.account.AccountServiceIF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController implements AccountControllerAPI {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountServiceIF accountService;

    @Override
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        logger.info("Got request for all accounts");
        return null;
    }

    @Override
    public ResponseEntity<AccountDTO> getAccountById(Long id) {
        logger.info("Got request for account with id: {}", id);
        return null;
    }

    @Override
    public ResponseEntity<AccountDTO> createAccount(AccountDTO accountDTO) {
        logger.info("Got request to create account: {}", accountDTO);

        if (accountService.getAccountByAccountNumber(accountDTO.getAccountNumber()).isPresent()) {
            logger.error("Account with account number {} already exists", accountDTO.getAccountNumber());
            return ResponseEntity.badRequest().build();
        }

        Account account = accountService.createAccount(new Account(accountDTO));
        logger.info("Created account: {}", account);

        return ResponseEntity.ok(new AccountDTO(account));
    }

    @Override
    public ResponseEntity<AccountDTO> deleteAccount(Long id) {
        logger.info("Got request to delete account with id: {}", id);
        return null;
    }
}
