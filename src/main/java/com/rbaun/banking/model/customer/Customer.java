package com.rbaun.banking.model.customer;

import com.rbaun.banking.controller.customer.request.CreateCustomerRequest;
import com.rbaun.banking.controller.customer.request.UpdateCustomerRequest;
import com.rbaun.banking.model.BaseEntity;
import com.rbaun.banking.model.account.Account;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a customer in the banking application
 */
@Entity(name = "Customers")
public class Customer extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @Column
    private LocalDate dateOfBirth;
    @Column(unique = true, nullable = false)
    private String socialSecurityNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();

    public Customer() {
    }

    public Customer(String name, String email, String phoneNumber, String address, LocalDate dateOfBirth, String socialSecurityNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Customer(CreateCustomerRequest request) {
        this.name = request.firstName() + " " + request.lastName();
        this.email = request.email();
        this.phoneNumber = request.phoneNumber();
        this.address = request.address();
        this.dateOfBirth = request.dateOfBirth();
        this.socialSecurityNumber = request.socialSecurityNumber();
    }

    public Customer(UpdateCustomerRequest request) {
        this.name = request.firstName() + " " + request.lastName();
        this.email = request.email();
        this.phoneNumber = request.phoneNumber();
        this.address = request.address();
        this.dateOfBirth = request.dateOfBirth();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setCustomer(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setCustomer(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}