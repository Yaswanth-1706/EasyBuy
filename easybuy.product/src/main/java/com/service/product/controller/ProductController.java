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

import com.service.product.entity.Product;
import com.service.product.payload.ProductDTO;
import com.service.product.service.ProductService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productservice;
	@PostMapping("/addProduct")
  public ResponseEntity<Product> addProduct(@RequestBody Product product){
	  Product products=productservice.addProduct(product);
	  return new ResponseEntity<>(products,HttpStatus.CREATED);
  }
	@GetMapping("/getProducts")
  public ResponseEntity<List<Product>> getProducts(){
	  List<Product> products=productservice.getProducts();
	  return new ResponseEntity<>(products,HttpStatus.OK);
  }	
	@GetMapping("/getProduct/{id}")
	 public ResponseEntity<Product> getEachSingleProduct(@PathVariable int id){
		  Product products=productservice.getEachSingleProduct(id);
		  return new ResponseEntity<>(products,HttpStatus.OK);
	  }	
	@PutMapping("/updateProduct/{id}")
	 public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody ProductDTO productdto){
		  Product products=productservice.updateProduct(id,productdto);
		  return new ResponseEntity<>(products,HttpStatus.OK);
	  }	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int id){
		  Product products=productservice.deleteProduct(id);
		  return new ResponseEntity<>(products,HttpStatus.OK);
	  }	
	@GetMapping("/getProductByCategory/{categoryname}")
	 public ResponseEntity<List<Product>> getProductsByCategoryName(@PathVariable String categoryname){
		  List<Product> products=productservice.getProductsByCategoryName(categoryname);
		  return new ResponseEntity<>(products,HttpStatus.OK);
	  }	
	@GetMapping("/getProductByProductName/{productname}")
	 public ResponseEntity<List<Product>> getProductsByproductName(@PathVariable String productname){
		  List<Product> products=productservice.getProductsByProductName(productname);
		  return new ResponseEntity<>(products,HttpStatus.OK);
	  }	
	
	
}
