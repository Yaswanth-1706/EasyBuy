package com.service.customer.controller;

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

import com.service.customer.entity.Order;
import com.service.customer.service.OrderService;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderservice;
	@PostMapping("/createOrder")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Order orders=orderservice.createOrder(order);
		return new ResponseEntity<>(orders,HttpStatus.CREATED);
	}
	@DeleteMapping("/deleteOrder/{id}")
	public ResponseEntity<Order> removerOrder(@PathVariable int id) {
		Order order=orderservice.removerOrder(id);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	@GetMapping("/getOrderById/{id}")
	public ResponseEntity<List<Order>> getOrder(@PathVariable int id){
	    List<Order> order=orderservice.getOrder(id);
	    return new ResponseEntity<>(order,HttpStatus.OK);
	}

}
