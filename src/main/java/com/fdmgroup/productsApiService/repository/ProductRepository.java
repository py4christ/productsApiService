package com.fdmgroup.productsApiService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.productsApiService.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> findProductByName(String name);
//	Product findProductByName(String name);

}
