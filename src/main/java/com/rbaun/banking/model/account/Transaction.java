package com.rbaun.banking.model.account;

import com.rbaun.banking.model.BaseEntity;
import com.rbaun.banking.model.enums.TransactionType;
import jakarta.persistence.*;

@Entity(name = "Transactions")
public class Transaction extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transaction() {
    }

    public Transaction(double amount, TransactionType type) {
        this.amount = amount;
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + super.getId() +
                ", account=" + account +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }
}
