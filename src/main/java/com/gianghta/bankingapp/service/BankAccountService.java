package com.gianghta.bankingapp.service;

import com.gianghta.bankingapp.model.BankAccount;
import com.gianghta.bankingapp.model.BankTransaction;
import com.gianghta.bankingapp.model.TransactionType;
import com.gianghta.bankingapp.repository.BankAccountRepository;
import com.gianghta.bankingapp.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    private final TransactionRepository transactionRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    public BankAccount createAccount(String accountHolder, BigInteger accountNumber) {
        BankAccount account = new BankAccount();
        account.setAccountHolder(accountHolder);
        account.setAccountNumber(accountNumber);
        account.setBalance(0.0);
        return bankAccountRepository.save(account);
    }

    public BankAccount deposit(BigInteger accountNumber, double amount) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account != null && amount > 0) {
            account.setBalance(account.getBalance() + amount);
            bankAccountRepository.save(account);

            BankTransaction transaction = new BankTransaction();
            transaction.setType(TransactionType.DEPOSIT);
            transaction.setAmount(amount);
            transaction.setDate(LocalDateTime.now());
            transaction.setBankAccount(account);
            transactionRepository.save(transaction);
        }
        return account;
    }

    public BankAccount withdraw(BigInteger accountNumber, double amount) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account != null && amount > 0 && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            bankAccountRepository.save(account);

            BankTransaction transaction = new BankTransaction();
            transaction.setType(TransactionType.WITHDRAW);
            transaction.setAmount(amount);
            transaction.setDate(LocalDateTime.now());
            transaction.setBankAccount(account);
            transactionRepository.save(transaction);
        }
        return account;
    }

    public double checkBalance(BigInteger accountNumber) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        return account != null ? account.getBalance() : 0.0;
    }
}

