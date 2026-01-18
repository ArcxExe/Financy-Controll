package com.example.finance.controlles;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import com.example.finance.model.*;
import com.example.finance.services.CategoryService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/category")
public class CategoryContorollers {

  private final CategoryService service;

  CategoryContorollers(CategoryService categoryService) {
    this.service = categoryService;
  }

  @PostMapping("create")
  public Category createCategory(@RequestParam String name , @RequestParam BigDecimal limits) {
    return service.create(name, limits);
  }

  @GetMapping
  public List<Category> getAll() {
    return service.getAll();
  }

  @PostMapping
  public Category getByName(@RequestParam String name){
    return service.getByName(name);
  }

  @DeleteMapping
  public List<Category> delete(@RequestParam String name) {
     return service.delete(name);
  }

  @PutMapping(path = "/change_name")
  public Category changeName(@RequestParam String newName , @RequestParam String categoryName){
    return service.changeName(newName, categoryName);
  }

  @PutMapping(path = "/change_limits")
  public Category changeLimits(@RequestParam BigDecimal limits , @RequestParam String categoryName){
    return service.changeLimit(limits, categoryName);
  }
}
