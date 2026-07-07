package com.service.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.cart.entity.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

	List<Cart> findAllByUserId(int id);

}
