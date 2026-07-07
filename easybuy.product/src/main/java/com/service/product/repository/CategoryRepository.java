package com.service.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.product.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

	List<Category> findAllByCategoryContainingIgnoreCase(String categoryname);

}
