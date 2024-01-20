package com.rbaun.banking.controller.account.endpoint;

import com.rbaun.banking.controller.account.response.TransactionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/accounts/{accountNumber}/transactions")
public interface TransactionAPI {

    @GetMapping("/all")
    ResponseEntity<List<TransactionResponse>> getAllTransactions(@PathVariable String accountNumber);
}
