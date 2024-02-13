package com.rbaun.banking.controller.v1.account;

import com.rbaun.banking.assertion.account.AccountAssertions;
import com.rbaun.banking.controller.v1.BaseController;
import com.rbaun.banking.controller.v1.account.request.CreateAccountRequest;
import com.rbaun.banking.controller.v1.account.request.DepositRequest;
import com.rbaun.banking.controller.v1.account.request.TransferRequest;
import com.rbaun.banking.controller.v1.account.request.WithdrawRequest;
import com.rbaun.banking.controller.v1.account.response.AccountBalanceResponse;
import com.rbaun.banking.controller.v1.account.response.AccountResponse;
import com.rbaun.banking.controller.v1.account.response.DeleteAccountResponse;
import com.rbaun.banking.controller.v1.account.response.TransactionResponse;
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
public class AccountController extends BaseController implements AccountControllerAPI {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountAssertions accountAssertions;

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
        logger.info("{} requested a list of all accounts", getLoggedInUsername());
        List<AccountResponse> accountList = accountService.getAllAccounts().stream().map(AccountResponse::new).toList();
        logger.info("Found {} accounts to return", accountList.size());

        return accountList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(accountList);
    }

    @Override
    public ResponseEntity<AccountResponse> getAccountById(Long id) {
        logger.info("{} requested to find account with id: {}", getLoggedInUsername(), id);
        Account account = accountService.getAccountById(id);
        logger.info("Found account: {}", account);

        return ResponseEntity.ok(AccountResponse.from(account));
    }

    @Override
    public ResponseEntity<AccountResponse> getAccountByAccountNumber(String accountNumber) {
        logger.info("{} requested to find account with account number: {}", getLoggedInUsername(), accountNumber);
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        logger.info("Found account: {}", account);

        return ResponseEntity.ok(AccountResponse.from(account));
    }

    @Override
    public ResponseEntity<AccountBalanceResponse> deposit(DepositRequest request) {
        logger.info("{} requested to deposit {} into account with account number: {}", getLoggedInUsername(), request.amount(), request.accountNumber());
        accountAssertions.throwIfSignedInCustomerDoesNotOwnAccount(request.accountNumber(), getLoggedInCustomer());
        Account account = accountService.deposit(request.accountNumber(), request.amount());
        logger.info("Deposited {} into account: {}", request.amount(), account);

        return ResponseEntity.ok(AccountBalanceResponse.from(account));
    }

    @Override
    public ResponseEntity<AccountBalanceResponse> withdraw(WithdrawRequest request) {
        logger.info("{} requested to withdraw {} from account with account number: {}", getLoggedInUsername(), request.amount(), request.accountNumber());
        Account account = accountService.withdraw(request.accountNumber(), request.amount());
        logger.info("Withdrew {} from account: {}", request.amount(), account);

        return ResponseEntity.ok(AccountBalanceResponse.from(account));
    }

    @Override
    public ResponseEntity<AccountBalanceResponse> transfer(TransferRequest request) {
        logger.info("{} requested to transfer {} from account with account number: {} to account with account number: {}", getLoggedInUsername(), request.amount(), request.fromAccountNumber(), request.toAccountNumber());
        Account account = accountService.transfer(request.fromAccountNumber(), request.toAccountNumber(), request.amount());
        logger.info("Transferred {} from account: {} to account: {}", request.amount(), request.fromAccountNumber(), request.toAccountNumber());

        return ResponseEntity.ok(AccountBalanceResponse.from(account));
    }

    @Override
    public ResponseEntity<AccountResponse> createAccount(CreateAccountRequest request) {
        logger.info("{} requested to create account: {}", getLoggedInUsername(), request);
        Account account = accountService.createAccount(new Account(request), getLoggedInCustomer());
        logger.info("Created account: {}", account);

        return ResponseEntity.ok(AccountResponse.from(account));
    }

    @Override
    public ResponseEntity<DeleteAccountResponse> deleteAccount(String accountNumber) {
        logger.info("{} requested to delete account with account number: {}", getLoggedInUsername(), accountNumber);
        accountService.deleteAccountByAccountNumber(accountNumber);
        logger.info("Deleted account: {}", accountNumber);

        return ResponseEntity.ok(DeleteAccountResponse.from(accountNumber));
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> getAllTransactions(String accountNumber) {
        logger.info("{} requested a list of all transactions for account number: {}", getLoggedInUsername(), accountNumber);
        List<TransactionResponse> transactionList = accountService.getAccountTransactionHistory(accountNumber).stream().map(TransactionResponse::new).toList();
        logger.info("Found {} transactions to return", transactionList.size());

        return ResponseEntity.ok(transactionList);
    }
}
