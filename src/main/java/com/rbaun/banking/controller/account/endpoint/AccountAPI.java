package com.rbaun.banking.controller.account.endpoint;

import com.rbaun.banking.controller.account.request.CreateAccountRequest;
import com.rbaun.banking.controller.account.response.AccountResponse;
import com.rbaun.banking.controller.account.response.DeleteAccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(AccountAPI.BASE_URL)
public interface AccountAPI {

    String BASE_URL = "/accounts";
    String GET_ALL_ACCOUNTS_URL = "/all";
    String GET_ACCOUNT_BY_ID_URL = "/{id}";
    String CREATE_ACCOUNT_URL = "/create";
    String DELETE_ACCOUNT_URL = "/delete/{accountNumber}";

    /**
     * Get all accounts
     *
     * @return List of all accounts
     */
    @GetMapping(GET_ALL_ACCOUNTS_URL)
    ResponseEntity<List<AccountResponse>> getAllAccounts();

    /**
     * Get account by id
     *
     * @param id Account id
     * @return Account matching the given id
     */
    @GetMapping(GET_ACCOUNT_BY_ID_URL)
    ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id);

    /**
     * Create a new account
     *
     * @param request Account to create
     * @return Account created
     */
    @PostMapping(CREATE_ACCOUNT_URL)
    ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request);

    /**
     * Delete account by account number
     *
     * @param accountNumber Account number
     * @return Account deleted
     */
    @DeleteMapping(DELETE_ACCOUNT_URL)
    ResponseEntity<DeleteAccountResponse> deleteAccount(@PathVariable String accountNumber);
}
