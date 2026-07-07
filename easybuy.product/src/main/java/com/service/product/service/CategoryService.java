package com.service.product.service;

import java.util.List;

import com.service.product.entity.Category;
import com.service.product.payload.CategoryDTO;

public interface CategoryService {
public Category createCategory(Category category);
 public List<Category> getCategories();
 public Category getEachSingleCategory(int id);
 public Category updateCategory(int id,CategoryDTO categorydto);
 public Category deleteCategory(int id);
 
}
