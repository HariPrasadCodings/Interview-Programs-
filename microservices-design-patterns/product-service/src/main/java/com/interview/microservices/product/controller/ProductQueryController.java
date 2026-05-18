package com.interview.microservices.product.controller;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.common.dto.ProductDto;
import com.interview.microservices.product.service.ProductQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for <b>read operations</b> (query side of CQRS).
 *
 * <p>All endpoints in this controller are read-only and served from the
 * MongoDB read model. Keeping read and write controllers separate is a
 * key CQRS practice:
 * <ul>
 *   <li>Clear separation of concerns</li>
 *   <li>Independent scaling - reads typically outnumber writes</li>
 *   <li>Different serialization or caching strategies per side</li>
 * </ul>
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductQueryController {

    private final ProductQueryService productQueryService;

    /**
     * Retrieves a single product by its identifier.
     *
     * @param id product identifier
     * @return the product wrapped in an {@link ApiResponse}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable String id) {
        log.info("GET /api/products/{} - fetching product from read model", id);
        ProductDto product = productQueryService.getProductById(id);
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    /**
     * Returns all products from the read model.
     *
     * @return list of products
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProducts() {
        log.info("GET /api/products - fetching all products from read model");
        List<ProductDto> products = productQueryService.getAllProducts();
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    /**
     * Returns all products in a given category.
     *
     * @param category the category to filter by
     * @return list of matching products
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getProductsByCategory(
            @PathVariable String category) {
        log.info("GET /api/products/category/{} - fetching products by category", category);
        List<ProductDto> products = productQueryService.getProductsByCategory(category);
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    /**
     * Searches products by name (case-insensitive partial match).
     *
     * @param name search term
     * @return list of matching products
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductDto>>> searchProducts(
            @RequestParam String name) {
        log.info("GET /api/products/search?name={} - searching products", name);
        List<ProductDto> products = productQueryService.searchProductsByName(name);
        return ResponseEntity.ok(ApiResponse.success(products));
    }
}
