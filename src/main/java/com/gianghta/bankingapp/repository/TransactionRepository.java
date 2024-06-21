package com.gianghta.bankingapp.repository;

import com.gianghta.bankingapp.model.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<BankTransaction, Long> {
    List<BankTransaction> findByBankAccountId(Long accountId);
}
