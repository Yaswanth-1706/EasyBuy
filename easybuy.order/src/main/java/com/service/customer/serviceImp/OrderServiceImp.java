package com.service.customer.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.customer.entity.Order;
import com.service.customer.exception.OrderNotFound;
import com.service.customer.repository.OrderRepository;
import com.service.customer.service.OrderService;

@Service
public class OrderServiceImp implements OrderService {
	@Autowired
	private OrderRepository orderRepo;
	public Order createOrder(Order order) {
		Order orders=orderRepo.save(order);
		return orders;
	}
	public Order removerOrder(int id) {
		Order order=orderRepo.findById(id).orElseThrow(()->new OrderNotFound(String.format("Order with this orderId id %d not found", id)));
		orderRepo.deleteById(id);
		return order;
	}
	public List<Order> getOrder(int id){
		List<Order> order=orderRepo.findAllByUserId(id);
	    return order;
	}
}
