package com.gianghta.bankingapp.repository;

import com.gianghta.bankingapp.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findByAccountNumber(BigInteger accountNumber);
}
