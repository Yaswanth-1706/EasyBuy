package com.service.cart.service;

import java.util.List;

import com.service.cart.entity.Cart;

public interface CartService {
 public Cart addToCart(Cart cart);
 public Cart removeFromCart(int id);
 public List<Cart> getItemsFromCart(int id);
}
