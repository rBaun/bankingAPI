package com.rbaun.banking.controller.v1.account;

import com.rbaun.banking.controller.v1.BaseControllerAPI;
import com.rbaun.banking.controller.v1.account.request.CreateAccountRequest;
import com.rbaun.banking.controller.v1.account.request.DepositRequest;
import com.rbaun.banking.controller.v1.account.request.TransferRequest;
import com.rbaun.banking.controller.v1.account.request.WithdrawRequest;
import com.rbaun.banking.controller.v1.account.response.AccountBalanceResponse;
import com.rbaun.banking.controller.v1.account.response.AccountResponse;
import com.rbaun.banking.controller.v1.account.response.DeleteAccountResponse;
import com.rbaun.banking.controller.v1.account.response.TransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * API for account operations
 */
@Tag(name = "Operations for the Account")
@RequestMapping(AccountControllerAPI.BASE_ACCOUNT_URL)
public interface AccountControllerAPI extends BaseControllerAPI {
    /**
     * Account operations
     */
    String BASE_ACCOUNT_URL = ROOT_URL + "/accounts";
    String GET_ALL_ACCOUNTS_URL = "/all";
    String GET_ACCOUNT_BY_ACCOUNT_NUMBER_URL = "/{accountNumber}";
    String CREATE_ACCOUNT_URL = "/create";
    String DELETE_ACCOUNT_URL = "/delete/{accountNumber}";
    String DEPOSIT_URL = "/deposit";
    String WITHDRAW_URL = "/withdraw";
    String TRANSFER_URL = "/transfer";

    /**
     * Transaction operations
     */
    String BASE_TRANSACTION_API = "/{accountNumber}/transactions";
    String GET_ALL_TRANSACTIONS_URL = BASE_TRANSACTION_API;



    /**
     * Request a list of all the accounts
     * @return @{@link ResponseEntity} with a list of @{@link AccountResponse}
     */
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

    /**
     * Request to get an account by account number
     * @param accountNumber - the account number to find account by
     * @return @{@link ResponseEntity} with an @{@link AccountResponse}
     */
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

    /**
     * Request to create a new account
     * @param request - the request body
     * @return @{@link ResponseEntity} with an @{@link AccountResponse}
     */
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
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Account already exists"
                    )
            }
    )
    @PostMapping(CREATE_ACCOUNT_URL)
    ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request);

    /**
     * Request to delete an account by account number
     * @param accountNumber - the account number of the account to delete
     * @return @{@link ResponseEntity} with a @{@link DeleteAccountResponse}
     */
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

    /**
     * Request to deposit money into an account
     * @param request - Contains the account number to deposit money into and the amount to deposit
     * @return @{@link ResponseEntity} with an @{@link AccountBalanceResponse}
     */
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
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid amount"
                    )
            }
    )
    @PostMapping(DEPOSIT_URL)
    ResponseEntity<AccountBalanceResponse> deposit(@RequestBody DepositRequest request);

    /**
     * Request to withdraw money from an account
     * @param request - Contains the account number to withdraw money from and the amount to withdraw
     * @return @{@link ResponseEntity} with an @{@link AccountBalanceResponse}
     */
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
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid amount or insufficient funds"
                    )
            }
    )
    @PostMapping(WITHDRAW_URL)
    ResponseEntity<AccountBalanceResponse> withdraw(@RequestBody WithdrawRequest request);

    /**
     * Request to transfer money from one account to another
     * @param request - Contains the account number to transfer money from, the account number to transfer money to, and the amount to transfer
     * @return @{@link ResponseEntity} with an @{@link AccountBalanceResponse} for the account the money was transferred from
     */
    @Operation(
            summary = "Transfer money from one account to another",
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
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid amount or insufficient funds"
                    )
            }
    )
    @PostMapping(TRANSFER_URL)
    ResponseEntity<AccountBalanceResponse> transfer(@RequestBody TransferRequest request);

    /**
     * Request to get all transactions for an account
     * @param accountNumber - the account number to get transactions for
     * @return @{@link ResponseEntity} with a list of @{@link TransactionResponse}
     */
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
