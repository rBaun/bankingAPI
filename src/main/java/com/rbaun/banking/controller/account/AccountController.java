package com.rbaun.banking.controller.account;

import com.rbaun.banking.controller.BaseController;
import com.rbaun.banking.controller.account.request.CreateAccountRequest;
import com.rbaun.banking.controller.account.response.AccountBalanceResponse;
import com.rbaun.banking.controller.account.response.AccountResponse;
import com.rbaun.banking.controller.account.response.DeleteAccountResponse;
import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.service.account.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController extends BaseController implements AccountControllerAPI {

    @Autowired
    private AccountService accountService;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Override
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        logger.info("Got request for all accounts");
        List<AccountResponse> accountList = accountService.getAllAccounts().stream().map(AccountResponse::new).toList();
        logger.info("Found {} accounts to return", accountList.size());

        return accountList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(accountList);
    }

    @Override
    public ResponseEntity<AccountResponse> getAccountById(Long id) {
        logger.info("Got request to find account with id: {}", id);
        Account account = accountService.getAccountById(id);
        logger.info("Found account: {}", account);

        return ResponseEntity.ok(new AccountResponse(account));
    }

    @Override
    public ResponseEntity<AccountResponse> getAccountByAccountNumber(String accountNumber) {
        logger.info("Got request to find account with account number: {}", accountNumber);
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        logger.info("Found account: {}", account);

        return ResponseEntity.ok(new AccountResponse(account));
    }

    @Override
    public ResponseEntity<AccountBalanceResponse> deposit(String accountNumber, double amount) {
        logger.info("Got request to deposit {} into account with account number: {}", amount, accountNumber);
        Account account = accountService.deposit(accountNumber, amount);
        logger.info("Deposited {} into account: {}", amount, account);

        return ResponseEntity.ok(new AccountBalanceResponse(account));
    }

    @Override
    public ResponseEntity<AccountBalanceResponse> withdraw(String accountNumber, double amount) {
        logger.info("Got request to withdraw {} from account with account number: {}", amount, accountNumber);
        Account account = accountService.withdraw(accountNumber, amount);
        logger.info("Withdrew {} from account: {}", amount, account);

        return ResponseEntity.ok(new AccountBalanceResponse(account));
    }

    @Override
    public ResponseEntity<AccountResponse> createAccount(CreateAccountRequest createAccountRequest) {
        logger.info("Got request to create account: {}", createAccountRequest);
        Account account = accountService.createAccount(new Account(createAccountRequest));
        logger.info("Created account: {}", account);

        return ResponseEntity.ok(new AccountResponse(account));
    }

    @Override
    public ResponseEntity<DeleteAccountResponse> deleteAccount(String accountNumber) {
        logger.info("Got request to delete account with account number: {}", accountNumber);
        accountService.deleteAccountByAccountNumber(accountNumber);
        logger.info("Deleted account: {}", accountNumber);

        return ResponseEntity.ok(new DeleteAccountResponse(accountNumber));
    }
}
