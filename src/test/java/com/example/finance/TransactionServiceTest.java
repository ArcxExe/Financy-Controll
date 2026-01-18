package com.example.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.finance.model.Category;
import com.example.finance.model.Transaction;
import com.example.finance.repository.CategoryRepository;
import com.example.finance.repository.TransactionRepository;
import com.example.finance.services.TransactionService;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

  @Mock
  private TransactionRepository transactionRepository;
  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private TransactionService transactionService;

  private Category mockCategory;

  @BeforeEach
  void setUp() {
    String catName = "Taxi";
    mockCategory = new Category(catName, BigDecimal.TEN);
  }

  @Test
  void create_ShouldCreateTransactionWithCategory() {

    transactionService.create(BigDecimal.valueOf(500), LocalDate.now(), mockCategory.getName());

    Mockito.verify(transactionRepository).save(any(Transaction.class));

  }

  @Test
  void withdrawAllTransactions() {
    Transaction tr1 = new Transaction(BigDecimal.valueOf(500), mockCategory, LocalDate.now());
    Transaction tr2 = new Transaction(BigDecimal.valueOf(0), new Category("Food", BigDecimal.valueOf(100)),
        LocalDate.now());

    Mockito.when(transactionRepository.findAll()).thenReturn(Arrays.asList(tr1, tr2));

    List<Transaction> allTransactions = transactionRepository.findAll();

    assertNotNull(allTransactions);
    assertEquals(2, allTransactions.size());

  }
}
