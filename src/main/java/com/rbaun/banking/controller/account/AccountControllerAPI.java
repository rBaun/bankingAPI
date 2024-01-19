package com.rbaun.banking.controller.account;

import com.rbaun.banking.controller.account.request.CreateAccountRequest;
import com.rbaun.banking.controller.account.response.AccountBalanceResponse;
import com.rbaun.banking.controller.account.response.AccountResponse;
import com.rbaun.banking.controller.account.response.DeleteAccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/accounts")
public interface AccountControllerAPI {

    /**
     * Get all accounts
     * @return List of all accounts
     */
    @GetMapping("/all")
    ResponseEntity<List<AccountResponse>> getAllAccounts();

    /**
     * Get account by id
     * @param id Account id
     * @return Account matching the given id
     */
    @GetMapping("/{id}")
    ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id);

    /**
     * Create a new account
     * @param createAccountRequest Account to create
     * @return Account created
     */
    @PostMapping("/create")
    ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest);

    /**
     * Delete account by account number
     * @param accountNumber Account number
     * @return Account deleted
     */
    @DeleteMapping("/delete/{accountNumber}")
    ResponseEntity<DeleteAccountResponse> deleteAccount(@PathVariable String accountNumber);

    /**
     * Get account by account number
     * @param accountNumber Account number
     * @return Account matching the given account number
     */
    @GetMapping("/accountNumber/{accountNumber}")
    ResponseEntity<AccountResponse> getAccountByAccountNumber(@PathVariable String accountNumber);

    /**
     * Deposit money into account
     * @param accountNumber Account number
     * @param amount Amount to deposit
     * @return Account balance
     */
    @PutMapping("/accountNumber/{accountNumber}/deposit/{amount}")
    ResponseEntity<AccountBalanceResponse> deposit(@PathVariable String accountNumber, @PathVariable double amount);

    /**
     * Withdraw money from account
     * @param accountNumber Account number
     * @param amount Amount to withdraw
     * @return Account balance
     */
    @PutMapping("/accountNumber/{accountNumber}/withdraw/{amount}")
    ResponseEntity<AccountBalanceResponse> withdraw(@PathVariable String accountNumber, @PathVariable double amount);
}
