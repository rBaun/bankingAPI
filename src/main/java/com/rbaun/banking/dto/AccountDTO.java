package com.rbaun.banking.dto;

import com.rbaun.banking.model.Account;
import jakarta.validation.constraints.NotBlank;

public class AccountDTO {

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    @NotBlank(message = "Account type is required")
    private String accountType;
    private double balance;

    public AccountDTO() {}

    public AccountDTO(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.accountType = account.getAccountType();
        this.balance = account.getBalance();
    }

    public AccountDTO(String accountNumber, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }
}

