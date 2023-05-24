package com.rytesoft.rytewebspringapp.controller;

import com.rytesoft.rytewebspringapp.model.Account;
import com.rytesoft.rytewebspringapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Controller
public class BankController {

    @Autowired
    private AccountService accountService;

    public BankController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/banking/create")
    public String showCreateAccountForm(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("accountNumber", generateAccountNumber());
        return "createaccount";
    }

    @PostMapping("/banking/create")
    public String createAccount(@ModelAttribute("account") Account account) {
        accountService.createAccount(account);
        return "redirect:/accounts";
    }

    @GetMapping("/banking")
    public String getAllAccounts(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "accounts";
    }

    @GetMapping("/banking/{accountNumber}")
    public String getAccountByNumber(@PathVariable String accountNumber, Model model) {
        Account account = accountService.getAccountByNumber(accountNumber);
        if (account == null) {
            return "redirect:/banking";
        }
        model.addAttribute("account", account);
        return "account-details";
    }

    @PostMapping("/banking/{accountNumber}/deposit")
    public String deposit(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        accountService.deposit(accountNumber, amount);
        return "redirect:/banking/" + accountNumber;
    }

    @PostMapping("/banking/{accountNumber}/withdraw")
    public String withdraw(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        accountService.withdraw(accountNumber, amount);
        return "redirect:/banking/" + accountNumber;
    }

    @PostMapping("/banking/{fromAccountNumber}/transfer/{toAccountNumber}")
    public String transfer(@PathVariable String fromAccountNumber,
                           @PathVariable String toAccountNumber,
                           @RequestParam BigDecimal amount) {
        accountService.transfer(fromAccountNumber, toAccountNumber, amount);
        return "redirect:/banking/" + fromAccountNumber;
    }


    private String generateAccountNumber() {
        String numbers = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        sb.append("PL");
        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(numbers.length());
            sb.append(numbers.charAt(index));
        }
        return sb.toString();
    }

}

