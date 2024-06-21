package com.gianghta.bankingapp.controller;

import com.gianghta.bankingapp.service.ExcelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/export")
public class ExcelExportController {
    @Autowired
    private ExcelExportService excelExportService;

    @GetMapping("/transactions")
    public String exportTransactions(@RequestParam Long accountId, @RequestParam String filePath) {
        excelExportService.exportTransactionsToExcel(accountId, filePath);
        return "Transactions exported to " + filePath;
    }
}
