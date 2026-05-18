package com.interview.microservices.product.service;

import com.interview.microservices.common.dto.ProductDto;
import com.interview.microservices.product.readmodel.ProductReadModel;
import com.interview.microservices.product.repository.ProductReadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Query service for the <b>read side</b> of CQRS.
 *
 * <p>All read operations are served from the MongoDB read model, which is
 * a denormalised projection kept in sync via Kafka events. Because the
 * read model is tailored for queries it can serve results faster than
 * the normalised write model.
 *
 * <p><b>CQRS benefit:</b> the read side can be scaled independently
 * of the write side. Multiple MongoDB replicas can serve high read
 * throughput without affecting write performance.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductQueryService {

    private final ProductReadRepository productReadRepository;

    /**
     * Retrieves a single product by its identifier.
     *
     * @param id product identifier (String form of the write-side Long id)
     * @return product DTO
     * @throws RuntimeException if the product is not found in the read model
     */
    public ProductDto getProductById(String id) {
        log.info("Querying read model for product id: {}", id);

        ProductReadModel readModel = productReadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Product not found in read model with id: " + id));

        return mapToDto(readModel);
    }

    /**
     * Returns all products from the read model.
     *
     * @return list of product DTOs
     */
    public List<ProductDto> getAllProducts() {
        log.info("Querying read model for all products");

        return productReadRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    /**
     * Returns all products belonging to a given category.
     *
     * @param category the category to filter by
     * @return list of matching product DTOs
     */
    public List<ProductDto> getProductsByCategory(String category) {
        log.info("Querying read model for products in category: {}", category);

        return productReadRepository.findByCategory(category).stream()
                .map(this::mapToDto)
                .toList();
    }

    /**
     * Searches products whose name contains the given text (case-insensitive).
     *
     * @param name search term
     * @return list of matching product DTOs
     */
    public List<ProductDto> searchProductsByName(String name) {
        log.info("Querying read model for products matching name: {}", name);

        return productReadRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::mapToDto)
                .toList();
    }

    /**
     * Maps a {@link ProductReadModel} document to a {@link ProductDto}.
     */
    private ProductDto mapToDto(ProductReadModel readModel) {
        return ProductDto.builder()
                .id(Long.parseLong(readModel.getId()))
                .name(readModel.getName())
                .description(readModel.getDescription())
                .price(readModel.getPrice())
                .category(readModel.getCategory())
                .quantity(readModel.getQuantity())
                .build();
    }
}
