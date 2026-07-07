package com.service.cart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="carts")
@Data
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int  cartId;
private int userId;
private int productId;

}
