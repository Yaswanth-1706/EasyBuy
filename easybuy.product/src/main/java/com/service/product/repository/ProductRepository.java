package com.service.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.product.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findAllByCategoryName(String categoryname);

	List<Product> findAllByProductNameContainingIgnoreCase(String productname);


}
