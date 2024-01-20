package com.rbaun.banking.controller.account.endpoint;

import com.rbaun.banking.controller.account.response.AccountBalanceResponse;
import com.rbaun.banking.controller.account.response.AccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AccountNumberAPI.BASE_URL)
public interface AccountNumberAPI {

    String BASE_URL = AccountAPI.BASE_URL + "/accountNumber";
    String GET_ACCOUNT_BY_ACCOUNT_NUMBER_URL = "/{accountNumber}";
    String DEPOSIT_URL = "/{accountNumber}/deposit/{amount}";
    String WITHDRAW_URL = "/{accountNumber}/withdraw/{amount}";

    /**
     * Get account by account number
     *
     * @param accountNumber Account number
     * @return Account matching the given account number
     */
    @GetMapping(GET_ACCOUNT_BY_ACCOUNT_NUMBER_URL)
    ResponseEntity<AccountResponse> getAccountByAccountNumber(@PathVariable String accountNumber);

    /**
     * Deposit money into account
     *
     * @param accountNumber Account number
     * @param amount        Amount to deposit
     * @return Account balance
     */
    @PutMapping(DEPOSIT_URL)
    ResponseEntity<AccountBalanceResponse> deposit(@PathVariable String accountNumber, @PathVariable double amount);

    /**
     * Withdraw money from account
     *
     * @param accountNumber Account number
     * @param amount        Amount to withdraw
     * @return Account balance
     */
    @PutMapping(WITHDRAW_URL)
    ResponseEntity<AccountBalanceResponse> withdraw(@PathVariable String accountNumber, @PathVariable double amount);
}
