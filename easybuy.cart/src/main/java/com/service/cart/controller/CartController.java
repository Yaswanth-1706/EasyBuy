package com.service.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.cart.entity.Cart;
import com.service.cart.service.CartService;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService  cartservice;
	@PostMapping("/addcart")
public ResponseEntity<Cart> addToCart(@RequestBody Cart cart){
	return new ResponseEntity<>(cartservice.addToCart(cart),HttpStatus.CREATED);
}
	@DeleteMapping("/deletecart/{id}")
public  ResponseEntity<Cart> removeFromCart(@PathVariable int id){
	return new ResponseEntity<>(cartservice.removeFromCart(id),HttpStatus.OK);
}
	@GetMapping("/getCartById/{id}")
	public ResponseEntity<List<Cart>> getItemsFromCart(@PathVariable int id){
		return new ResponseEntity<>(cartservice.getItemsFromCart(id),HttpStatus.OK);
	}
}
