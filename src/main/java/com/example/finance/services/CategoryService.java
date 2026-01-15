package com.example.finance.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.finance.model.Category;
import com.example.finance.repository.CategoryRepository;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Category create(String name, BigDecimal limits) {
    Category newCategory = new Category(name, limits);
    return categoryRepository.save(newCategory);

  }

  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  public List<Category> delete(String name) {

    Category optionalCategory = categoryRepository.findByName(name);

    categoryRepository.deleteById(optionalCategory.getId());

    List<Category> allCategory = getAll();

    return allCategory;
  }

  public Category changeName(String name, String nameCategory) {

    Category category = categoryRepository.findByName(nameCategory);
    category.setName(name);

    categoryRepository.save(category);
    return category;

  }

  public Category getByName(String name) {
    return categoryRepository.findByName(name);
  }

  public Category changeLimit(BigDecimal limits, String nameCategory) {

    Category category = categoryRepository.findByName(nameCategory);
    category.setLimits(limits);

    categoryRepository.save(category);
    return category;
  }

}
