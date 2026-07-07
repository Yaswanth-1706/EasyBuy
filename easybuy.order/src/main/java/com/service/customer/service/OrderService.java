package com.service.customer.service;

import java.util.List;

import com.service.customer.entity.Order;

public interface OrderService {
	public Order createOrder(Order order);
	public Order removerOrder(int id);
	public List<Order> getOrder(int id);
	

}
