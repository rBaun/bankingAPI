package com.rbaun.banking.controller.v1.account.response;

import com.rbaun.banking.model.account.Transaction;
import com.rbaun.banking.model.enums.TransactionType;

/**
 * Used for when the @{@link Transaction} is returned to the client as a response
 * @param type - the transaction type {@link TransactionType}
 * @param amount - the transaction amount
 */
public record TransactionResponse(TransactionType type, double amount) {
    /**
     * Constructor to map @{@link Transaction} to @{@link TransactionResponse}
     * @param transaction - the transaction to map
     */
    public TransactionResponse(Transaction transaction) {
        this(transaction.getType(), transaction.getAmount());
    }
}
