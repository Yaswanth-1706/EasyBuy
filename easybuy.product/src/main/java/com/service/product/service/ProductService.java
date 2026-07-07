package com.service.product.service;

import java.util.List;

import com.service.product.entity.Product;
import com.service.product.payload.ProductDTO;

public interface ProductService {
   public Product addProduct(Product product);
   public List<Product> getProducts();
   public Product getEachSingleProduct(int id);
   public Product updateProduct(int id,ProductDTO productdto);
   public Product deleteProduct(int id);
   public List<Product> getProductsByProductName(String productname);
   public List<Product> getProductsByCategoryName(String categoryname);
}
