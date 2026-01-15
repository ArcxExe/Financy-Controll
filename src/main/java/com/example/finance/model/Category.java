package com.example.finance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private BigDecimal limits;
  @OneToMany(mappedBy = "category")
  private List<Transaction> transactions;

  public Category() {
  }

  public Category(String name, BigDecimal limits) {
    this.name = name;
    this.limits = limits;
  }

}
