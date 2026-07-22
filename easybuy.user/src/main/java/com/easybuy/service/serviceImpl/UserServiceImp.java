package com.easybuy.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.easybuy.service.Exceptions.UserNotFound;
import com.easybuy.service.entity.User;
import com.easybuy.service.feignClient.CartFeign;
import com.easybuy.service.feignClient.OrderFeign;
import com.easybuy.service.feignClient.ProductFeign;
import com.easybuy.service.payload.CartDTO;
import com.easybuy.service.payload.CategoryDTO;
import com.easybuy.service.payload.OrderDTO;
import com.easybuy.service.payload.ProductDTO;
import com.easybuy.service.payload.UpdateUserDTO;
import com.easybuy.service.payload.UserDTO;
import com.easybuy.service.payload.UserRegisterDTO;
import com.easybuy.service.repository.UserRepository;
import com.easybuy.service.service.UserService;

@Service
public class UserServiceImp implements UserService{
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public UserRegisterDTO createUser(UserRegisterDTO userRegisterDto) {
		userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
		User user=userdtoToEntity(userRegisterDto);
		User savedUser=userRepo.save(user);
		UserRegisterDTO userdto=entityToDto(savedUser);
		return userdto;
	}
	public User userdtoToEntity(UserRegisterDTO userRegisterDto) {
		User user=new User();
		user.setUserName(userRegisterDto.getUserName());
		user.setUserEmail(userRegisterDto.getUserEmail());
		user.setPassword(userRegisterDto.getPassword());
		user.setMobileNumber(userRegisterDto.getMobileNumber());
		user.setUserAddress(userRegisterDto.getUserAddress());
		user.setRole("user");
		return user;
	}
	public UserRegisterDTO entityToDto(User user) {
		UserRegisterDTO useregisterdto=new UserRegisterDTO();
		useregisterdto.setUserName(user.getUserName());
		useregisterdto.setUserEmail(user.getUserEmail());
		useregisterdto.setPassword(user.getPassword());
		useregisterdto.setMobileNumber(user.getMobileNumber());
		useregisterdto.setUserAddress(user.getUserAddress());
		return useregisterdto;
	}
	public UpdateUserDTO update(UpdateUserDTO userdto,int id) {
		User user =userRepo.findById(id).orElseThrow(()->new UserNotFound(String.format("user id %d not found" ,id)));
		user.setUserName(userdto.getUserName());
		user.setMobileNumber(userdto.getMobileNumber());
		user.setUserAddress(userdto.getUserAddress());
		user.setUserEmail(userdto.getUserEmail());   
		userRepo.save(user);
		return userdto;
		
		
	}
	public UserDTO getUsersByEmail (String email) {
		User user=userRepo.findByUserEmail(email).orElseThrow(()->new UserNotFound(String.format("user eamil %s not found" ,email)));
		UserDTO userDto=new UserDTO();
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setUserEmail(email);
		userDto.setUserAddress(user.getUserAddress());
		userDto.setMobileNumber(user.getMobileNumber());
		return userDto;
	}
	
	 @Autowired
	    private ProductFeign productFeign;
	 @Autowired
	 private CartFeign cartFeign;
     @Autowired
	 private OrderFeign orderfeign;
	    public List<ProductDTO> getProducts() {
	        return productFeign.getProducts();
	    }
	    public ProductDTO getEachSingleProduct(int id){
			  ProductDTO products=productFeign.getEachSingleProduct(id);
			  return products;
		  }
	    public ProductDTO updateProduct(int id,ProductDTO productdto){
			  ProductDTO products=productFeign.updateProduct(id,productdto);
			  return products;
		  }
	    public ProductDTO deleteProduct( int id){
			  ProductDTO products=productFeign.deleteProduct(id);
			  return  products;
		  }
	    public List<ProductDTO> getProductsByCategoryName(String categoryname){
			  List<ProductDTO> products=productFeign.getProductsByCategoryName(categoryname);
			  return products;
		  }
	    public List<ProductDTO> getProductsByProductName(String productname){
			  List<ProductDTO> products=productFeign.getProductsByProductName(productname);
			  return products;
		  }	
	    public CategoryDTO createCategory(CategoryDTO category){
			return productFeign.createCategory(category);
		}
	    public List<CategoryDTO> getCategories(){
			  List<CategoryDTO> categories=productFeign.getCategories();
			  return  categories;
		  }
	    public CategoryDTO getEachSingleCategory( int id){
			  CategoryDTO categories =productFeign.getEachSingleCategory(id);
			  return categories;
		  }
	    public CategoryDTO updateCategory(int id,CategoryDTO categorydto){
			  CategoryDTO categories=productFeign.updateCategory(id,categorydto);
			  return categories;
		  }	
	    public CategoryDTO deleteCategory(int id){
			  CategoryDTO categories=productFeign.deleteCategory(id);
			  return categories;
		  }
	    public CartDTO addToCart(CartDTO cart) {
	    	CartDTO carts=cartFeign.addToCart(cart);
	    	return carts;
	    }
	    public CartDTO removeFromCart(int id) {
	    	CartDTO carts=cartFeign.removeFromCart(id);
	    	return carts;
	    }
	    public List<CartDTO> getItemsFromCart(int id){
	    	List<CartDTO> carts=cartFeign.getItemsFromCart(id);
	    	return carts;
	    }
	    
	    public OrderDTO createOrder(OrderDTO order) {
			OrderDTO orders=orderfeign.createOrder(order);
			return orders;
		}
		public OrderDTO removerOrder(int id) {
			OrderDTO order=orderfeign.removerOrder(id);
			return order;
		}
		public List<OrderDTO> getOrder(int id){
			List<OrderDTO> order=orderfeign.getOrder(id);
		    return order;
		}

}
