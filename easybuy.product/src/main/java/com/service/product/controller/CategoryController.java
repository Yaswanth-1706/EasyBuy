package com.service.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.product.entity.Category;
import com.service.product.entity.Product;
import com.service.product.payload.CategoryDTO;
import com.service.product.service.CategoryService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@PostMapping("/addCategory")
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
		return new ResponseEntity<>(categoryService.createCategory(category),HttpStatus.CREATED);
	}
	@GetMapping("/getCategory")
	  public ResponseEntity<List<Category>> getCategories(){
		  List<Category> categories=categoryService.getCategories();
		  return new ResponseEntity<>(categories,HttpStatus.OK);
	  }	
		@GetMapping("/getCategory/{id}")
		 public ResponseEntity<Category> getEachSingleCategory(@PathVariable int id){
			  Category categories =categoryService.getEachSingleCategory(id);
			  return new ResponseEntity<>(categories,HttpStatus.OK);
		  }	
		@PutMapping("/updateCategory/{id}")
		 public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categorydto){
			  Category categories=categoryService.updateCategory(id,categorydto);
			  return new ResponseEntity<>(categories,HttpStatus.OK);
		  }	
		@DeleteMapping("/deleteCategory/{id}")
		public ResponseEntity<Category> deleteCategory(@PathVariable int id){
			  Category categories=categoryService.deleteCategory(id);
			  return new ResponseEntity<>(categories,HttpStatus.OK);
		  }	
		
		
}
