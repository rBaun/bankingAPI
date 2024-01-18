package com.rbaun.banking.controller;

import com.rbaun.banking.dto.AccountDTO;
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
    ResponseEntity<List<AccountDTO>> getAllAccounts();

    /**
     * Get account by id
     * @param id Account id
     * @return Account matching the given id
     */
    @GetMapping("/{id}")
    ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id);

    /**
     * Create a new account
     * @param accountDTO Account to create
     * @return Account created
     */
    @PostMapping("/create")
    ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO accountDTO);

    /**
     * Delete account by id
     * @param id Account id
     * @return Account deleted
     */
    @DeleteMapping("/delete/{id}")
    ResponseEntity<AccountDTO> deleteAccount(@PathVariable Long id);
}
