package com.rytesoft.rytewebspringapp.controller;
import com.rytesoft.rytewebspringapp.model.Account;
import com.rytesoft.rytewebspringapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccountByNumber(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByNumber(accountNumber);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity deposit(@PathVariable String accountNumber, @RequestBody BigDecimal amount) {
        accountService.deposit(accountNumber, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity withdraw(@PathVariable String accountNumber, @RequestBody BigDecimal amount) {
        accountService.withdraw(accountNumber, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{fromAccountNumber}/transfer/{toAccountNumber}")
    public ResponseEntity transfer(@PathVariable String fromAccountNumber,
                                   @PathVariable String toAccountNumber,
                                   @RequestBody BigDecimal amount) {
        accountService.transfer(fromAccountNumber, toAccountNumber, amount);
        return ResponseEntity.ok().build();
    }
}

