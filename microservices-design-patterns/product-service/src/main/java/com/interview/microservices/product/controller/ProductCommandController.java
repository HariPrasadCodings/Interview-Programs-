package com.interview.microservices.product.controller;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.common.dto.ProductDto;
import com.interview.microservices.product.command.CreateProductCommand;
import com.interview.microservices.product.command.UpdateProductCommand;
import com.interview.microservices.product.service.ProductCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for <b>write operations</b> (command side of CQRS).
 *
 * <p>All endpoints in this controller mutate state. After persisting
 * changes to the H2 write store, domain events are published to Kafka
 * so the read model stays in sync.
 *
 * <p>In a production system this controller could be deployed as a
 * separate process from the query controller, allowing independent
 * scaling of write and read workloads.
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductCommandController {

    private final ProductCommandService productCommandService;

    /**
     * Creates a new product.
     *
     * @param command validated creation payload
     * @return the created product wrapped in an {@link ApiResponse}
     */
    @PostMapping
    public ResponseEntity<ApiResponse<ProductDto>> createProduct(
            @Valid @RequestBody CreateProductCommand command) {
        log.info("POST /api/products - creating product: {}", command.getName());
        ProductDto created = productCommandService.createProduct(command);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Product created successfully", created));
    }

    /**
     * Updates an existing product.
     *
     * @param id      product identifier from the path
     * @param command validated update payload
     * @return the updated product wrapped in an {@link ApiResponse}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductCommand command) {
        log.info("PUT /api/products/{} - updating product", id);
        command.setId(id);
        ProductDto updated = productCommandService.updateProduct(command);
        return ResponseEntity.ok(ApiResponse.success("Product updated successfully", updated));
    }

    /**
     * Deletes a product.
     *
     * @param id product identifier
     * @return confirmation message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        log.info("DELETE /api/products/{} - deleting product", id);
        productCommandService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success("Product deleted successfully", null));
    }
}
