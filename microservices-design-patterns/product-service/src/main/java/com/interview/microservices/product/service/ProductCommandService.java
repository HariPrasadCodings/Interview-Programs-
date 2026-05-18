package com.interview.microservices.product.service;

import com.interview.microservices.common.dto.ProductDto;
import com.interview.microservices.product.command.CreateProductCommand;
import com.interview.microservices.product.command.UpdateProductCommand;
import com.interview.microservices.product.entity.Product;
import com.interview.microservices.product.repository.ProductWriteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command service for the <b>write side</b> of CQRS.
 *
 * <p>Responsibilities:
 * <ol>
 *   <li>Accept validated commands ({@link CreateProductCommand},
 *       {@link UpdateProductCommand}).</li>
 *   <li>Persist state changes to the relational write store (H2).</li>
 *   <li>Publish domain events to Kafka so the read model (MongoDB)
 *       can be updated asynchronously.</li>
 * </ol>
 *
 * <p><b>Why Kafka?</b> The write and read databases are eventually
 * consistent. Kafka acts as a durable event log, guaranteeing that
 * every state change is delivered to all interested consumers.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCommandService {

    private static final String TOPIC_PRODUCT_EVENTS = "product-events";

    private final ProductWriteRepository productWriteRepository;
    private final KafkaTemplate<String, ProductDto> kafkaTemplate;

    /**
     * Handles a {@link CreateProductCommand}: persists a new product and
     * publishes a {@code PRODUCT_CREATED} event.
     *
     * @param command validated creation command
     * @return DTO representation of the newly created product
     */
    @Transactional
    public ProductDto createProduct(CreateProductCommand command) {
        log.info("Handling CreateProductCommand for product: {}", command.getName());

        Product product = Product.builder()
                .name(command.getName())
                .description(command.getDescription())
                .price(command.getPrice())
                .category(command.getCategory())
                .quantity(command.getQuantity())
                .build();

        Product saved = productWriteRepository.save(product);
        log.info("Product persisted to write store with id: {}", saved.getId());

        ProductDto dto = mapToDto(saved);
        kafkaTemplate.send(TOPIC_PRODUCT_EVENTS, String.valueOf(saved.getId()), dto);
        log.info("Published PRODUCT_CREATED event to Kafka for product id: {}", saved.getId());

        return dto;
    }

    /**
     * Handles an {@link UpdateProductCommand}: updates the existing product
     * and publishes a {@code PRODUCT_UPDATED} event.
     *
     * @param command validated update command
     * @return DTO representation of the updated product
     * @throws RuntimeException if the product is not found
     */
    @Transactional
    public ProductDto updateProduct(UpdateProductCommand command) {
        log.info("Handling UpdateProductCommand for product id: {}", command.getId());

        Product product = productWriteRepository.findById(command.getId())
                .orElseThrow(() -> new RuntimeException(
                        "Product not found with id: " + command.getId()));

        product.setName(command.getName());
        product.setDescription(command.getDescription());
        product.setPrice(command.getPrice());
        product.setCategory(command.getCategory());
        product.setQuantity(command.getQuantity());

        Product updated = productWriteRepository.save(product);
        log.info("Product updated in write store for id: {}", updated.getId());

        ProductDto dto = mapToDto(updated);
        kafkaTemplate.send(TOPIC_PRODUCT_EVENTS, String.valueOf(updated.getId()), dto);
        log.info("Published PRODUCT_UPDATED event to Kafka for product id: {}", updated.getId());

        return dto;
    }

    /**
     * Deletes a product from the write store and publishes a
     * {@code PRODUCT_DELETED} event (DTO with id only, other fields null).
     *
     * @param id product identifier
     * @throws RuntimeException if the product is not found
     */
    @Transactional
    public void deleteProduct(Long id) {
        log.info("Handling delete for product id: {}", id);

        if (!productWriteRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }

        productWriteRepository.deleteById(id);
        log.info("Product deleted from write store for id: {}", id);

        // Publish a delete event with only the id populated
        ProductDto deleteEvent = ProductDto.builder().id(id).build();
        kafkaTemplate.send(TOPIC_PRODUCT_EVENTS, String.valueOf(id), deleteEvent);
        log.info("Published PRODUCT_DELETED event to Kafka for product id: {}", id);
    }

    /**
     * Maps a {@link Product} entity to a {@link ProductDto}.
     */
    private ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .build();
    }
}
