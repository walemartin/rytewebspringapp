package com.rytesoft.rytewebspringapp.service;

import com.rytesoft.rytewebspringapp.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    void createAccount(Account account);
    List<Account> getAllAccounts();
    Account getAccountByNumber(String accountNumber);
    void deposit(String accountNumber, BigDecimal amount);
    void withdraw(String accountNumber, BigDecimal amount);
    void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount);
}
