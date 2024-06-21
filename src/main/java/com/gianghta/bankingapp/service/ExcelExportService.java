package com.gianghta.bankingapp.service;

import com.gianghta.bankingapp.model.BankTransaction;
import com.gianghta.bankingapp.repository.TransactionRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {
    private final TransactionRepository transactionRepository;

    public ExcelExportService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void exportTransactionsToExcel(Long accountId, String filePath) {
        List<BankTransaction> transactions = transactionRepository.findByBankAccountId(accountId);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Transactions");

        Row headerRow = sheet.createRow(0);
        String[] columnNames = {"Type", "Amount", "Date"};
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnNames[i]);
        }

        int rowNum = 1;
        for (BankTransaction transaction : transactions) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(transaction.getType().name());
            row.createCell(1).setCellValue(transaction.getAmount());
            row.createCell(2).setCellValue(transaction.getDate().toString());
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
