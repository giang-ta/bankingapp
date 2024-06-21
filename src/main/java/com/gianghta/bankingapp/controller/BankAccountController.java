package com.gianghta.bankingapp.controller;


import com.gianghta.bankingapp.model.BankAccount;
import com.gianghta.bankingapp.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/bank-account")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/create")
    public BankAccount createAccount(@RequestParam String accountHolder, @RequestParam BigInteger accountNumber) {
        return bankAccountService.createAccount(accountHolder, accountNumber);
    }

    @PostMapping("/deposit")
    public BankAccount deposit(@RequestParam BigInteger accountNumber, @RequestParam double amount) {
        return bankAccountService.deposit(accountNumber, amount);
    }

    @PostMapping("/withdraw")
    public BankAccount withdraw(@RequestParam BigInteger accountNumber, @RequestParam double amount) {
        return bankAccountService.withdraw(accountNumber, amount);
    }

    @GetMapping("/balance")
    public double checkBalance(@RequestParam BigInteger accountNumber) {
        return bankAccountService.checkBalance(accountNumber);
    }
}

