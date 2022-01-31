package com.fdmgroup.productsApiService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.productsApiService.exception.ProductNotFoundException;
import com.fdmgroup.productsApiService.model.Product;
import com.fdmgroup.productsApiService.repository.ProductRepository;

@Service
public class ProductService {

	ProductRepository productRepo;

	@Autowired
	public ProductService(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}

	public List<Product> readProducts() {
		return productRepo.findAll();
	}

	public Product readProductById(int id) {
		Optional<Product> optProduct = productRepo.findById(id);
		if (!optProduct.isPresent()) {
			throw new ProductNotFoundException("Product not found for id " + id);
		}
		return optProduct.get();
	}

	public Product createProduct(Product product) {
		return productRepo.save(product);
	}

	public void updateProduct(Product product) {
		productRepo.save(product);
	}
	
	public void deleteProduct(int id) {
		productRepo.deleteById(id);
	}
	
	public List<Product> findProductByName(String name) {
		return productRepo.findProductByName(name);
	}
	

}
