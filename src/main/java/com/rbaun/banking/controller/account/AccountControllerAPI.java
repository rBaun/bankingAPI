package com.rbaun.banking.controller.account;

import com.rbaun.banking.controller.BaseAPI;
import com.rbaun.banking.controller.account.request.CreateAccountRequest;
import com.rbaun.banking.controller.account.response.AccountBalanceResponse;
import com.rbaun.banking.controller.account.response.AccountResponse;
import com.rbaun.banking.controller.account.response.DeleteAccountResponse;
import com.rbaun.banking.controller.account.response.TransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Operations for the Account")
@RequestMapping(AccountControllerAPI.BASE_ACCOUNT_URL)
public interface AccountControllerAPI extends BaseAPI {
    /**
     * Account operations
     */
    String BASE_ACCOUNT_URL = ROOT_URL + "/accounts";
    String GET_ALL_ACCOUNTS_URL = "/all";
    String GET_ACCOUNT_BY_ID_URL = "/{id}";
    String CREATE_ACCOUNT_URL = "/create";
    String DELETE_ACCOUNT_URL = "/delete/{accountNumber}";

    /**
     * Account number operations
     */
    String BASE_ACCOUNT_NUMBER_URL = "/accountNumber";
    String GET_ACCOUNT_BY_ACCOUNT_NUMBER_URL = BASE_ACCOUNT_NUMBER_URL + "/{accountNumber}";
    String DEPOSIT_URL = BASE_ACCOUNT_NUMBER_URL + "/{accountNumber}/deposit/{amount}";
    String WITHDRAW_URL = BASE_ACCOUNT_NUMBER_URL + "/{accountNumber}/withdraw/{amount}";

    /**
     * Transaction operations
     */
    String BASE_TRANSACTION_API = "/{accountNumber}/transactions";
    String GET_ALL_TRANSACTIONS_URL = BASE_TRANSACTION_API + "";

    @Operation(
            summary = "Get a list of all accounts",
            responses = {
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
            }
    )
    @GetMapping(GET_ALL_ACCOUNTS_URL)
    ResponseEntity<List<AccountResponse>> getAllAccounts();

    @Operation(
            summary = "Get an account by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account found",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Account not found"
                    )
            }
    )
    @GetMapping(GET_ACCOUNT_BY_ID_URL)
    ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id);

    @Operation(
            summary = "Create a new account",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Account created",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request body"
                    )
            }
    )
    @PostMapping(CREATE_ACCOUNT_URL)
    ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request);

    @Operation(
            summary = "Delete an account by account number",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account deleted",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Account not found"
                    )
            }
    )
    @DeleteMapping(DELETE_ACCOUNT_URL)
    ResponseEntity<DeleteAccountResponse> deleteAccount(@PathVariable String accountNumber);

    @Operation(
            summary = "Get an account by account number",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account found",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Account not found"
                    )
            }
    )
    @GetMapping(GET_ACCOUNT_BY_ACCOUNT_NUMBER_URL)
    ResponseEntity<AccountResponse> getAccountByAccountNumber(@PathVariable String accountNumber);

    @Operation(
            summary = "Deposit money into account",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account found",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Account not found"
                    )
            }
    )
    @PutMapping(DEPOSIT_URL)
    ResponseEntity<AccountBalanceResponse> deposit(@PathVariable String accountNumber, @PathVariable double amount);

    @Operation(
            summary = "Withdraw money from account",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account found",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Account not found"
                    )
            }
    )
    @PutMapping(WITHDRAW_URL)
    ResponseEntity<AccountBalanceResponse> withdraw(@PathVariable String accountNumber, @PathVariable double amount);

    @Operation(
            summary = "Get all transactions for an account",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of transactions",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No transactions found"
                    )
            }
    )
    @GetMapping(GET_ALL_TRANSACTIONS_URL)
    ResponseEntity<List<TransactionResponse>> getAllTransactions(@PathVariable String accountNumber);
}
