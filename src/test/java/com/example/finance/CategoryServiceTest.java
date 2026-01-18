package com.example.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.finance.model.Category;
import com.example.finance.repository.CategoryRepository;
import com.example.finance.services.CategoryService;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private CategoryService categoryService;

  @Test
  void create_ShouldReturnSavedCategory() {
    String name = "Food";
    BigDecimal limits = BigDecimal.valueOf(1000);

    Category expectedCategory = new Category(name, limits);
    expectedCategory.setId(1L);
    when(categoryRepository.save(any(Category.class)))
        .thenReturn(expectedCategory);

    Category result = categoryService.create(name, limits);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(1L, result.getId());
    Assertions.assertEquals("Food", result.getName());

    verify(categoryRepository, times(1)).save(any(Category.class));
  }

  @Test
  void getAllCategoryes() {
    Category cat1 = new Category("Food", BigDecimal.TEN);
    Category cat2 = new Category("Taxi", BigDecimal.valueOf(20));

    when(categoryService.getAll()).thenReturn(Arrays.asList(cat1, cat2));
    Assertions.assertEquals(Arrays.asList(cat1, cat2), categoryService.getAll());
  }

  @Test
  void getByNameCategory() {
    Category expectedCategory = new Category("Food", BigDecimal.TEN);

    when(categoryRepository.findByName(expectedCategory.getName())).thenReturn(expectedCategory);
    Category result = categoryService.getByName(expectedCategory.getName());

    Assertions.assertNotNull(result);
    Assertions.assertEquals(expectedCategory.getName(), result.getName());
  }

  @Test
  void deleteCategoryByName_ShouldRemoveCategoryFromList() {
    Category categoryToDelete = new Category("Food", BigDecimal.TEN);
    Category remainingCategory = new Category("Taxi", BigDecimal.valueOf(20));

    categoryToDelete.setId(1L);

    List<Category> initialCategories = Arrays.asList(categoryToDelete, remainingCategory);

    doNothing().when(categoryRepository).deleteById(1L);

    when(categoryRepository.findAll())
        .thenReturn(initialCategories)
        .thenReturn(Arrays.asList(remainingCategory));

    when(categoryRepository.findByName("Food")).thenReturn(categoryToDelete);

    categoryService.delete("Food");

    verify(categoryRepository, times(1)).deleteById(1L);

    List<Category> updatedCategories = categoryService.getAll();

    Assertions.assertFalse(updatedCategories.contains(categoryToDelete));
    Assertions.assertEquals(1, updatedCategories.size());
    Assertions.assertEquals("Taxi", updatedCategories.get(0).getName());
  }

  @Test
  void changeCategoryName() {
    Category category = new Category("Taxi", BigDecimal.TEN);
    String newCategoryName = "Food";

    when(categoryRepository.findByName("Taxi")).thenReturn(category);

    categoryService.changeName(newCategoryName, "Taxi");

    verify(categoryRepository, times(1)).save(category);

    assertEquals(category.getName(), newCategoryName);

  }

  @Test
  void changeCategoryLimits() {
    Category category = new Category("Taxi", BigDecimal.TEN);
    BigDecimal newCategoryLimits = BigDecimal.ONE;

    when(categoryRepository.findByName("Taxi")).thenReturn(category);

    categoryService.changeLimit(newCategoryLimits, category.getName());

    verify(categoryRepository, times(1)).save(category);

    assertEquals(category.getLimits(), newCategoryLimits);

  }

}
