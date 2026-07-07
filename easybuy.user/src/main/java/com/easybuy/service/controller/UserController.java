package com.easybuy.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easybuy.service.payload.CartDTO;
import com.easybuy.service.payload.CategoryDTO;
import com.easybuy.service.payload.JwtAuthResponse;
import com.easybuy.service.payload.OrderDTO;
import com.easybuy.service.payload.ProductDTO;
import com.easybuy.service.payload.UpdateUserDTO;
import com.easybuy.service.payload.UserDTO;
import com.easybuy.service.payload.UserLoginDTO;
import com.easybuy.service.payload.UserRegisterDTO;
import com.easybuy.service.securrity.JwtTokenProvider;
import com.easybuy.service.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private UserService userservice;
	@Autowired
	private AuthenticationManager authenticationmanager;
	@Autowired
	private JwtTokenProvider jwt;
	@PostMapping("/register")
public ResponseEntity<UserRegisterDTO> createUser(@RequestBody UserRegisterDTO userregisterdto){
	return new ResponseEntity<>(userservice.createUser(userregisterdto),HttpStatus.CREATED);
}
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> userLogin(@RequestBody UserLoginDTO userLoginDto ){
		
		Authentication authentication=authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUserEmail(), userLoginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token=jwt.generateToken(authentication);
		JwtAuthResponse jwtResponse=new JwtAuthResponse(token,userLoginDto);
		return new ResponseEntity<>(jwtResponse,HttpStatus.OK);
	}
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<UpdateUserDTO> update(@RequestBody UpdateUserDTO userdto,@PathVariable int id){
		UpdateUserDTO user=userservice.update(userdto,id);
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	 @GetMapping("/getUsersByEmail/{email}")
	   public ResponseEntity <UserDTO> getUsersByEmail(@PathVariable String email){
			  UserDTO user=userservice.getUsersByEmail(email);
			  return new ResponseEntity<>(user,HttpStatus.OK);
		  }
	 @GetMapping("/products/getProducts")
	    public ResponseEntity <List<ProductDTO>> getProducts() {
	        return new ResponseEntity<>(userservice.getProducts(),HttpStatus.OK);
	    }
	 @GetMapping("products/getProduct/{id}")
	 public ResponseEntity<ProductDTO> getEachSingleProduct(@PathVariable int id){
		  ProductDTO products=userservice.getEachSingleProduct(id);
		  return new ResponseEntity<>(products,HttpStatus.OK);
	  }	
	 @PutMapping("/products/updateProduct/{id}")
	 public ProductDTO updateProduct(@PathVariable int id, @RequestBody ProductDTO productdto){
		  ProductDTO products=userservice.updateProduct(id,productdto);
		  return products;
	  }
	 @DeleteMapping("/products/deleteProduct/{id}")
	 public ResponseEntity<ProductDTO> deleteProduct(@PathVariable int id){
		  ProductDTO products=userservice.deleteProduct(id);
		  return  new ResponseEntity<>(products,HttpStatus.OK);
	  }
	 @GetMapping("/products/getProductByCategory/{categoryname}")
   public ResponseEntity<List<ProductDTO>> getProductsByCategoryName(@PathVariable String categoryname){
		  List<ProductDTO> products=userservice.getProductsByCategoryName(categoryname);
		  return new ResponseEntity<>(products,HttpStatus.OK);
	  }
	 @GetMapping("/products/getProductByProductName/{productname}")
   public ResponseEntity<List<ProductDTO>> getProductsByproductName(@PathVariable String productname){
		  List<ProductDTO> products=userservice.getProductsByProductName(productname);
		  return new ResponseEntity<>(products,HttpStatus.OK);
	  }	
	 @PostMapping("/category/addCategory")
		public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO category){
			return new ResponseEntity<>(userservice.createCategory(category),HttpStatus.CREATED);
		}
	 @GetMapping("/category/getCategory")
	  public ResponseEntity<List<CategoryDTO>> getCategories(){
		  List<CategoryDTO> categories=userservice.getCategories();
		  return new ResponseEntity<>(categories,HttpStatus.OK);
	  }	
	 @GetMapping("/category/getCategory/{id}")
	 public ResponseEntity<CategoryDTO> getEachSingleCategory(@PathVariable int id){
		  CategoryDTO categories =userservice.getEachSingleCategory(id);
		  return new ResponseEntity<>(categories,HttpStatus.OK);
	  }	
	 @PutMapping("/category/updateCategory/{id}")
	 public ResponseEntity<CategoryDTO> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categorydto){
		  CategoryDTO categories=userservice.updateCategory(id,categorydto);
		  return new ResponseEntity<>(categories,HttpStatus.OK);
	  }	
	 @DeleteMapping("/category/deleteCategory/{id}")
		public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable int id){
			  CategoryDTO categories=userservice.deleteCategory(id);
			  return new ResponseEntity<>(categories,HttpStatus.OK);
		  }	
	 @PostMapping("/cart/addcart")
	 public ResponseEntity<CartDTO> addToCart(@RequestBody CartDTO cart){
		 CartDTO carts=userservice.addToCart(cart);
		 return new ResponseEntity<>(carts,HttpStatus.CREATED);
	 }
	@DeleteMapping("/cart/deletecart/{id}")
	 public ResponseEntity<CartDTO> removeFromCart(@PathVariable int id) {
		CartDTO carts=userservice.removeFromCart(id);
		return new ResponseEntity<>(carts,HttpStatus.OK);
	}
	@GetMapping("/cart/getCartById/{id}")
	 public ResponseEntity<List<CartDTO>> getItemsFromCart(@PathVariable int id){
		List<CartDTO> carts=userservice.getItemsFromCart(id);
		return new ResponseEntity<>(carts,HttpStatus.OK);
	}

	@PostMapping("/order/createOrder")
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO order) {
		OrderDTO orders=userservice.createOrder(order);
		return new ResponseEntity<>(orders,HttpStatus.CREATED);
	}
	@DeleteMapping("/order/deleteOrder/{id}")
	public ResponseEntity<OrderDTO> removerOrder(@PathVariable int id) {
		OrderDTO order=userservice.removerOrder(id);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	@GetMapping("/order/getOrderById/{id}")
	public ResponseEntity<List<OrderDTO>> getOrder(@PathVariable int id){
	    List<OrderDTO> order=userservice.getOrder(id);
	    return new ResponseEntity<>(order,HttpStatus.OK);
	}

	 

}
