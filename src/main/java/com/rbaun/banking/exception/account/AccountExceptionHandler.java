package com.rbaun.banking.exception.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handle exceptions thrown by the endpoints in the @{@link com.rbaun.banking.controller.account.AccountController}
 */
@ControllerAdvice
public class AccountExceptionHandler {

    /**
     * Handle @{@link AccountNotFoundException} and return a @{@link ResponseEntity} with a message
     * @param e - the @{@link AccountNotFoundException} to handle
     * @return @{@link ResponseEntity} with a message indicating the account was not found
     */
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * Handle @{@link InsufficientFundsException} and return a @{@link ResponseEntity} with a message
     * @param e - the @{@link InsufficientFundsException} to handle
     * @return @{@link ResponseEntity} with a message indicating the account has insufficient funds
     */
    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInsufficientFundsException(InsufficientFundsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Handle @{@link AmountInvalidException} and return a @{@link ResponseEntity} with a message
     * @param e - the @{@link AmountInvalidException} to handle
     * @return @{@link ResponseEntity} with a message indicating the amount is invalid
     */
    @ExceptionHandler(AmountInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleAmountInvalidException(AmountInvalidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Handle @{@link DuplicateAccountException} and return a @{@link ResponseEntity} with a message
     * @param e - the @{@link DuplicateAccountException} to handle
     * @return @{@link ResponseEntity} with a message indicating the account already exists
     */
    @ExceptionHandler(DuplicateAccountException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDuplicateAccountException(DuplicateAccountException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    /**
     * Handle @{@link Exception} and return a @{@link ResponseEntity} with a message
     * @param e - the @{@link Exception} to handle
     * @return @{@link ResponseEntity} with a message indicating an internal server error
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
