package com.example.finance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.math.BigDecimal;

//@Entity(name = "transaction") //name отвечается как сущность будет называться в запрос JPQL
@Entity
@Table(name = "transactions")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private BigDecimal sum;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category; // будет хранить все из category . В бд будет называться category_id

  private LocalDate date;

  public Transaction(BigDecimal sum, Category category, LocalDate date) {
    this.sum = sum;
    this.category = category;
    this.date = date;
  }

  public Transaction() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getSum() {
    return sum;
  }

  public void setSum(BigDecimal sum) {
    this.sum = sum;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

}
