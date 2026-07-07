package com.easybuy.service.service;

import java.util.List;

import com.easybuy.service.payload.CartDTO;
import com.easybuy.service.payload.CategoryDTO;
import com.easybuy.service.payload.OrderDTO;
import com.easybuy.service.payload.ProductDTO;
import com.easybuy.service.payload.UpdateUserDTO;
import com.easybuy.service.payload.UserDTO;
import com.easybuy.service.payload.UserRegisterDTO;

public interface UserService {
 public UserRegisterDTO createUser(UserRegisterDTO userRegisterDto);
 public List<ProductDTO> getProducts();
 public ProductDTO getEachSingleProduct(int id);
 public List<ProductDTO> getProductsByProductName(String productname);
 public List<ProductDTO> getProductsByCategoryName(String categoryname);
 public ProductDTO deleteProduct( int id);
 public ProductDTO updateProduct(int id,ProductDTO productdto);
 public CategoryDTO createCategory(CategoryDTO category);
 public List<CategoryDTO> getCategories();
 public CategoryDTO getEachSingleCategory( int id);
 public CategoryDTO updateCategory(int id,CategoryDTO categorydto);
 public CategoryDTO deleteCategory(int id);
 public UserDTO getUsersByEmail(String email);
 public UpdateUserDTO update(UpdateUserDTO userdto,int id);
 public CartDTO addToCart(CartDTO cart);
 public CartDTO removeFromCart(int id);
 public List<CartDTO> getItemsFromCart(int id);
 public OrderDTO createOrder(OrderDTO order);
 public OrderDTO removerOrder(int id);
 public List<OrderDTO> getOrder(int id);
}
