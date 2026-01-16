package com.example.finance;

import org.springframework.stereotype.Service;

import com.example.finance.dto.TransactionDTO;
import com.example.finance.model.Category;
import com.example.finance.model.Transaction;
import com.example.finance.repository.CategoryRepository;
import com.example.finance.repository.TransactionRepository;
import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;

@Service
public class TransactionService {

  private final TransactionRepository transactionRepository;
  private final CategoryRepository categoryRepository;

  public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
    this.transactionRepository = transactionRepository;
    this.categoryRepository = categoryRepository;
  }

  public List<TransactionDTO> getAllTransactions() {
    return transactionRepository.findAll()
        .stream()
        .map(this::convertToDTO)
        .toList();
  }

  private TransactionDTO convertToDTO(Transaction transaction) {
    if (transaction.getCategory() == null) {
      Category category = new Category("None", BigDecimal.valueOf(0.0));
      transaction.setCategory(category);
      categoryRepository.save(category);
    }

    return new TransactionDTO(
        transaction.getId(),
        transaction.getSum(),
        transaction.getDate(),
        transaction.getCategory().getName());
    // transaction.getCategory() != null ? transaction.getCategory().getName() : b);
  }

  public Transaction create(BigDecimal sum, LocalDate date, String categoryName) {
    Category category = categoryRepository.findByName(categoryName);
    Transaction transaction = new Transaction();
    transaction.setDate(date);
    transaction.setSum(sum);
    transaction.setCategory(category);

    transactionRepository.save(transaction);

    return transaction;
  }

}
