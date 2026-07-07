package com.easybuy.service.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.easybuy.service.payload.OrderDTO;

@FeignClient(name="order-service", url="http://localhost:8085")
public interface OrderFeign {

	@PostMapping("/order/createOrder")
	public OrderDTO createOrder(@RequestBody OrderDTO order);
	@DeleteMapping("/order/deleteOrder/{id}")
	public OrderDTO removerOrder(@PathVariable int id);
	@GetMapping("/order/getOrderById/{id}")
	public List<OrderDTO> getOrder(@PathVariable int id);
}
