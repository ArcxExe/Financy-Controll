package com.example.finance.controlles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance.dto.TransactionDTO;
import com.example.finance.services.TransactionService;
import com.example.finance.model.Transaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/transaction")
public class TransactionControllers {

  private final TransactionService transactionService;

  TransactionControllers(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping
  public List<TransactionDTO> getAll(){
    return transactionService.getAllTransactions();
  }

  @PostMapping
  public Transaction create(@RequestParam BigDecimal total , @RequestParam LocalDate date , @RequestParam String categoryName ) {
    return transactionService.create(total , date , categoryName); 
  }

}
