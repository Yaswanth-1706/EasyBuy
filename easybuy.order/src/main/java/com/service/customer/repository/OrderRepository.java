package com.service.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.customer.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findAllByUserId(int id);

}
