package com.rbaun.banking.model.account;

import com.rbaun.banking.controller.account.request.CreateAccountRequest;
import com.rbaun.banking.model.BaseEntity;
import com.rbaun.banking.model.enums.AccountType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Accounts")
public class Account extends BaseEntity {

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Transaction> transactionList = new ArrayList<>();
    @Column(unique = true)
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(nullable = false)
    private double balance;

    public Account() {
    }

    public Account(CreateAccountRequest createAccountRequest) {
        this.accountNumber = createAccountRequest.accountNumber();
        this.accountType = createAccountRequest.accountType();
        this.balance = createAccountRequest.balance();
    }

    public Account(String accountNumber, AccountType accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.setBalance(this.getBalance() + amount);
    }

    public void withdraw(double amount) {
        this.setBalance(this.getBalance() - amount);
    }

    public void addTransaction(Transaction transaction) {
        transaction.setAccount(this);
        this.transactionList.add(transaction);
    }

    public void addTransactionList(List<Transaction> transactionList) {
        transactionList.forEach(this::addTransaction);
    }

    /**
     * Remove transaction from account, if exists on account
     *
     * @param transaction Transaction to remove
     */
    public void removeTransaction(Transaction transaction) {
        if (this.transactionList.remove(transaction)) {
            transaction.setAccount(null);
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + super.getId() + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}

