package com.service.product.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.product.entity.Category;
import com.service.product.entity.Product;
import com.service.product.exception.ProductNotFound;
import com.service.product.payload.ProductDTO;
import com.service.product.repository.ProductRepository;
import com.service.product.service.ProductService;
@Service
public class ProductServiceImp implements ProductService {
	@Autowired
  private ProductRepository productrepo;
	public Product addProduct(Product product) {
		Product products=productrepo.save(product);
		return products;
	}
	public List<Product> getProducts() {
		List<Product> products=productrepo.findAll();
		return products;
	}
	public Product getEachSingleProduct(int id) {
		Product product=productrepo.findById(id).orElseThrow(()->new ProductNotFound(String.format("Product with this id: %d is not exist", id)));
		return product;
	}
	public Product updateProduct(int id,ProductDTO productdto) {
		Product products =productrepo.findById(id).orElseThrow(()->new ProductNotFound(String.format("Product with this id: %d is not exist", id)));
		products.setPrice(productdto.getPrice());
		products.setProductDescription(productdto.getProductDescription());
		products.setProductImage(productdto.getProductImage());
		products.setProductName(productdto.getProductName());
		products.setQuantity(productdto.getQuantity());
		products.setStock(productdto.getStock());
		return products;
	}
	public Product deleteProduct(int id) {
		Product product =productrepo.findById(id).orElseThrow(()->new ProductNotFound(String.format("Product with this id: %d is not exist", id)));
		productrepo.deleteById(product.getProductId());
		System.out.println("deleteed successfully");
		return product;
	}
	 public List<Product> getProductsByProductName(String productname){
		 List<Product> products=productrepo.findAllByProductNameContainingIgnoreCase(productname);
			return products;
	 }
	 public List<Product> getProductsByCategoryName(String categoryname){
		 List<Product> products=productrepo.findAllByCategoryName(categoryname);
			return products;
	 }
	 
}
