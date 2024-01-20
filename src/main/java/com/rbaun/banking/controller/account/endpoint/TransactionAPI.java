package com.rbaun.banking.controller.account.endpoint;

import com.rbaun.banking.controller.account.response.TransactionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(TransactionAPI.BASE_URL)
public interface TransactionAPI {

    String BASE_URL = AccountAPI.BASE_URL + "/{accountNumber}/transactions";
    String GET_ALL_TRANSACTIONS_URL = "";

    @GetMapping(GET_ALL_TRANSACTIONS_URL)
    ResponseEntity<List<TransactionResponse>> getAllTransactions(@PathVariable String accountNumber);
}
