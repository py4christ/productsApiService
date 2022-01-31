package com.fdmgroup.productsApiService.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.productsApiService.model.Product;
import com.fdmgroup.productsApiService.service.ProductService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

	ProductService productService;

	@Autowired
	public ProductRestController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {
		return ResponseEntity.ok(productService.readProducts());
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetched product from persistence", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) {
		return ResponseEntity.ok(productService.readProductById(id));
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
		Product createdProduct = productService.createProduct(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId())
				.toUri();
	//	return ResponseEntity.created(location).build();
		return ResponseEntity.created(location).body(createdProduct);
	}

	@PutMapping()
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
		productService.readProductById(product.getId());
		productService.updateProduct(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{product.getId}")
				.buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(location).body(product);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable int id) {
		productService.readProductById(id);
		productService.deleteProduct(id);
	}
	
	@GetMapping("/productByName")
	public ResponseEntity<List<Product>> getProductByName(String name) {
		return ResponseEntity.ok(productService.findProductByName(name));

	}

}
