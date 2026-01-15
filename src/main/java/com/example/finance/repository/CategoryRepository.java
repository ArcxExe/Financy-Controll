package com.example.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finance.model.Category;

public interface CategoryRepository extends JpaRepository<Category , Long> {

  
}
