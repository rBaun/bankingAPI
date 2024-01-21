package com.rbaun.banking.controller.account;

import com.rbaun.banking.controller.account.request.CreateAccountRequest;
import com.rbaun.banking.controller.account.response.AccountBalanceResponse;
import com.rbaun.banking.controller.account.response.AccountResponse;
import com.rbaun.banking.controller.account.response.DeleteAccountResponse;
import com.rbaun.banking.controller.account.response.TransactionResponse;
import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.service.account.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    private AccountService accountService;

    @Operation(summary = "Get a list of all accounts", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of accounts",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No accounts found"
            )
    })
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

        return ResponseEntity.ok(AccountResponse.from(account));
    }

    @Override
    public ResponseEntity<AccountResponse> getAccountByAccountNumber(String accountNumber) {
        logger.info("Got request to find account with account number: {}", accountNumber);
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        logger.info("Found account: {}", account);

        return ResponseEntity.ok(AccountResponse.from(account));
    }

    @Override
    public ResponseEntity<AccountBalanceResponse> deposit(String accountNumber, double amount) {
        logger.info("Got request to deposit {} into account with account number: {}", amount, accountNumber);
        Account account = accountService.deposit(accountNumber, amount);
        logger.info("Deposited {} into account: {}", amount, account);

        return ResponseEntity.ok(AccountBalanceResponse.from(account));
    }

    @Override
    public ResponseEntity<AccountBalanceResponse> withdraw(String accountNumber, double amount) {
        logger.info("Got request to withdraw {} from account with account number: {}", amount, accountNumber);
        Account account = accountService.withdraw(accountNumber, amount);
        logger.info("Withdrew {} from account: {}", amount, account);

        return ResponseEntity.ok(AccountBalanceResponse.from(account));
    }

    @Override
    public ResponseEntity<AccountResponse> createAccount(CreateAccountRequest request) {
        logger.info("Got request to create account: {}", request);
        Account account = accountService.createAccount(new Account(request));
        logger.info("Created account: {}", account);

        return ResponseEntity.ok(AccountResponse.from(account));
    }

    @Override
    public ResponseEntity<DeleteAccountResponse> deleteAccount(String accountNumber) {
        logger.info("Got request to delete account with account number: {}", accountNumber);
        accountService.deleteAccountByAccountNumber(accountNumber);
        logger.info("Deleted account: {}", accountNumber);

        return ResponseEntity.ok(DeleteAccountResponse.from(accountNumber));
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> getAllTransactions(String accountNumber) {
        logger.info("Got request to get all transactions for account with account number: {}", accountNumber);
        List<TransactionResponse> transactionList = accountService.getAccountTransactionHistory(accountNumber).stream().map(TransactionResponse::new).toList();
        logger.info("Found {} transactions to return", transactionList.size());

        return ResponseEntity.ok(transactionList);
    }
}
