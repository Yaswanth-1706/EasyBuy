package com.easybuy.service.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.easybuy.service.payload.CartDTO;

@FeignClient(name="cart-service", url="http://localhost:8084")
public interface CartFeign {
	@PostMapping("/cart/addcart")
	 public CartDTO addToCart(@RequestBody CartDTO cart);
	@DeleteMapping("/cart/deletecart/{id}")
	 public CartDTO removeFromCart(@PathVariable int id);
	@GetMapping("/cart/getCartById/{id}")
	 public List<CartDTO> getItemsFromCart(@PathVariable int id);
}

