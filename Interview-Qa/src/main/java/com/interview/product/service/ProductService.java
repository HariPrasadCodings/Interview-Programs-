package com.interview.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.interview.entity.Product;
import com.interview.exception.DuplicateProductFoundException;
import com.interview.exception.ProductNotFoundException;

@Service
public class ProductService {

	List<Product> productList = Stream
			.of(new Product("PTC49893", "Mobile", 8500, "SAMSUNG Galaxy F13 (Sunrise Copper, 64 GB)", "Electronics"),
					new Product("PTC25563", "Keyboard", 9500, "MAC Magic Keyboard", "Electronics"),
					new Product("PTC25372", "Books", 250, "It Ends With Us", "Education"),
					new Product("PTC49823", "Remote Control Toys", 699,
							"Wembley High Speed Mini 1:24 Scale Rechargeable Remote Control car with Lithium Battery",
							"Baby&Kids"))
			.collect(Collectors.toList());

	public List<Product> getProducts() {
		return productList;
	}
	
	public List<Product> saveProduct(Product product) {
		boolean containsId = productList.stream().map(Product::getId)
				.anyMatch(productId -> productId.equals(product.getId()));
		if (!containsId) {
			productList.add(product);
		} else {
			throw new DuplicateProductFoundException("Product Code already exist in system :" + product.getId());
		}
		return productList;
	}

	public List<Product> getProductByType(String productType) {
		List<Product> products = productList.stream().filter(product -> product.getProductType().equals(productType))
				.collect(Collectors.toList());
		return Optional.of(products).filter(list -> !list.isEmpty())
				.orElseThrow(() -> new ProductNotFoundException("Product Not available for the type: " + productType));
	}

	public List<Product> filterProductByType(String productType) {
		List<Product> matchedProduct = productList.stream()
				.filter(product -> product.getProductType().equals(productType)).collect(Collectors.toList());
		if (!matchedProduct.isEmpty()) {
			return matchedProduct;
		} else {
			return productList;
		}
	}

}
