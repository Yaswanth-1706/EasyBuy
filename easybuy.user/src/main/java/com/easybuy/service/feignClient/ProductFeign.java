package com.easybuy.service.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.easybuy.service.payload.CategoryDTO;
import com.easybuy.service.payload.ProductDTO;

@FeignClient(name="product-service", url="http://localhost:8083")
public interface ProductFeign {
	@PostMapping("/products/addProduct")
	ProductDTO addProduct(@RequestBody ProductDTO product);

	@GetMapping("/products/getProducts")
	List<ProductDTO>  getProducts();

	@GetMapping("/products/getProduct/{id}")
	ProductDTO getEachSingleProduct(@PathVariable int id);

	@PutMapping("/products/updateProduct/{id}")
	ProductDTO  updateProduct(@PathVariable int id, @RequestBody ProductDTO productdto);

	@DeleteMapping("/products/deleteProduct/{id}")
	ProductDTO  deleteProduct(@PathVariable int id);

	@GetMapping("/products/getProductByCategory/{categoryname}")
	List<ProductDTO>  getProductsByCategoryName(@PathVariable String categoryname);

	@GetMapping("/products/getProductByProductName/{productname}")
	List<ProductDTO>  getProductsByProductName(@PathVariable String productname);
	
	@PostMapping("/category/addCategory")
	public CategoryDTO createCategory(@RequestBody CategoryDTO category);

	@GetMapping("/category/getCategory")
	public List<CategoryDTO> getCategories();

	@GetMapping("/category/getCategory/{id}")
	public CategoryDTO getEachSingleCategory(@PathVariable int id);


	@PutMapping("/category/updateCategory/{id}")
	public CategoryDTO updateCategory(@PathVariable int id, @RequestBody CategoryDTO categorydto);
	
	@DeleteMapping("/category/deleteCategory/{id}")
	public CategoryDTO deleteCategory(@PathVariable int id);
	

}
