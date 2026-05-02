package com.interview.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interview.entity.Product;
import com.interview.product.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	// URL: http://localhost:2025/product/save

	@PostMapping("/save")
	public ResponseEntity<List<Product>> saveProduct(@RequestBody Product product) {
		return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
	}
	
	// URL: http://localhost:2025/product/search/Education

	@GetMapping("/search/{productType}")
	public ResponseEntity<?> getProductType(@PathVariable String productType) {
		List<Product> productByType = productService.getProductByType(productType);
		return ResponseEntity.ok(productByType);
	}

	// URL: http://localhost:2025/product/filter

	@GetMapping("/filter")
	public ResponseEntity<?> filterProducts(@RequestParam(value = "productType", required = false) String productType) {
		List<Product> products = productType != null ? productService.getProductByType(productType)
				: productService.getProducts();
		return ResponseEntity.ok(products);
	}

	// http://localhost:2025/product?format=xml
	@GetMapping(produces = { "application/json" })
	public List<Product> products(@RequestParam(value = "productType", required = false) String productType) {
		return productType != null ? productService.getProductByType(productType) : productService.getProducts();
	}
}
