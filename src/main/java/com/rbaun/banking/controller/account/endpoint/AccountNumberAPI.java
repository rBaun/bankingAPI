package com.rbaun.banking.controller.account.endpoint;

import com.rbaun.banking.controller.account.response.AccountBalanceResponse;
import com.rbaun.banking.controller.account.response.AccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/accounts/accountNumber")
public interface AccountNumberAPI {

    /**
     * Get account by account number
     *
     * @param accountNumber Account number
     * @return Account matching the given account number
     */
    @GetMapping("/{accountNumber}")
    ResponseEntity<AccountResponse> getAccountByAccountNumber(@PathVariable String accountNumber);

    /**
     * Deposit money into account
     *
     * @param accountNumber Account number
     * @param amount        Amount to deposit
     * @return Account balance
     */
    @PutMapping("/{accountNumber}/deposit/{amount}")
    ResponseEntity<AccountBalanceResponse> deposit(@PathVariable String accountNumber, @PathVariable double amount);

    /**
     * Withdraw money from account
     *
     * @param accountNumber Account number
     * @param amount        Amount to withdraw
     * @return Account balance
     */
    @PutMapping("/{accountNumber}/withdraw/{amount}")
    ResponseEntity<AccountBalanceResponse> withdraw(@PathVariable String accountNumber, @PathVariable double amount);
}
