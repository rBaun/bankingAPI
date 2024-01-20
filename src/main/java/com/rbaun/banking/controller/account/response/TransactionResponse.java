package com.rbaun.banking.controller.account.response;

import com.rbaun.banking.model.account.Transaction;
import com.rbaun.banking.model.enums.TransactionType;

public record TransactionResponse(TransactionType type, double amount) {
    public TransactionResponse(Transaction transaction) {
        this(transaction.getType(), transaction.getAmount());
    }
}
