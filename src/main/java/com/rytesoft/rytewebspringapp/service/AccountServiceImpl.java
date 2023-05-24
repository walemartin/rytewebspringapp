package com.rytesoft.rytewebspringapp.service;

import com.rytesoft.rytewebspringapp.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import java.util.ArrayList;

@Service
public class AccountServiceImpl implements AccountService {

    private List<Account> accounts = new ArrayList<>();

    @Override
    public void createAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accounts;
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        Optional<Account> optionalAccount = accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst();
        return optionalAccount.orElse(null);
    }

    @Override
    public void deposit(String accountNumber, BigDecimal amount) {
        Account account = getAccountByNumber(accountNumber);
        BigDecimal balance = account.getBalance();
        account.setBalance(balance.add(amount));
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {
        Account account = getAccountByNumber(accountNumber);
        BigDecimal balance = account.getBalance();
        account.setBalance(balance.subtract(amount));
    }

    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        withdraw(fromAccountNumber, amount);
        deposit(toAccountNumber, amount);
    }
}
