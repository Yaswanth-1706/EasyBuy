package com.service.product.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.product.entity.Category;
import com.service.product.exception.ProductNotFound;
import com.service.product.payload.CategoryDTO;
import com.service.product.repository.CategoryRepository;
import com.service.product.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{
	@Autowired
	private CategoryRepository catrepo;
public Category createCategory(Category category) {
	Category categorys=catrepo.save(category);
	return categorys;
}
public List<Category> getCategories(){
	List<Category> products=catrepo.findAll();
	return products;
}
public Category getEachSingleCategory(int id) {
	Category category=catrepo.findById(id).orElseThrow(()->new ProductNotFound(String.format("Category with this id: %d is not exist", id)));
	return category;
}
public Category updateCategory(int id,CategoryDTO categorydto) {
	Category categories =catrepo.findById(id).orElseThrow(()->new ProductNotFound(String.format("category with this id: %d is not exist", id)));
	categories.setCategory(categorydto.getCategory());
	return categories;
}
public Category deleteCategory(int id) {
	Category category =catrepo.findById(id).orElseThrow(()->new ProductNotFound(String.format("category with this id: %d is not exist", id)));
	catrepo.deleteById(category.getCategoryId());
	System.out.println("deleteed successfully");
	return category;
}

}
