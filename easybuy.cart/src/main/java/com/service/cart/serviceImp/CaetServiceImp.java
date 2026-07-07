package com.service.cart.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.cart.entity.Cart;
import com.service.cart.exception.CartNotFound;
import com.service.cart.repository.CartRepository;
import com.service.cart.service.CartService;

@Service
public class CaetServiceImp implements CartService {
	@Autowired
	private CartRepository cartRepo;
public Cart addToCart(Cart cart) {
	return cartRepo.save(cart);
}
public Cart removeFromCart(int id) {
	Cart cart =cartRepo.findById(id).orElseThrow(()->new CartNotFound(String.format("cart with this id %d not found", id)));
	 cartRepo.deleteById(id);
	 return cart;
}
public List<Cart> getItemsFromCart(int id) {
	List<Cart> cart=cartRepo.findAllByUserId(id);
	return cart;
	
}
}
