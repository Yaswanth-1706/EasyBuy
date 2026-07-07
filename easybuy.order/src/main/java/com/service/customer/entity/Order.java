package com.service.customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="custOrder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int orderId;
	@Column(name="userId",nullable=false)
private int userId;
	@Column(name="productId",nullable=false)
private int productId;
	@Column(name="productOrderId",nullable=false)
private String productOrderId;
}
