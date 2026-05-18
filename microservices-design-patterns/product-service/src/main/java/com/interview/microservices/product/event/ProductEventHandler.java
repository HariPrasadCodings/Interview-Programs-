package com.interview.microservices.product.event;

import com.interview.microservices.common.dto.ProductDto;
import com.interview.microservices.product.readmodel.ProductReadModel;
import com.interview.microservices.product.repository.ProductReadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Kafka consumer that listens to product domain events and updates
 * the MongoDB <b>read model</b>.
 *
 * <p>This is the bridge between the write side and the read side in CQRS.
 * When the write side publishes a {@link ProductDto} event to the
 * {@code product-events} topic, this handler projects the change into
 * the denormalised MongoDB collection.
 *
 * <h3>Event semantics</h3>
 * <ul>
 *   <li><b>Create / Update:</b> the event contains all product fields;
 *       the handler upserts the corresponding MongoDB document.</li>
 *   <li><b>Delete:</b> the event contains only {@code id} (other fields
 *       are null); the handler removes the document from MongoDB.</li>
 * </ul>
 *
 * <p><b>Eventual consistency:</b> there is a small window where the read
 * model may lag behind the write model. For most e-commerce use cases
 * this latency (milliseconds) is acceptable.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ProductEventHandler {

    private final ProductReadRepository productReadRepository;

    /**
     * Consumes product events from Kafka and synchronises the read model.
     *
     * @param productDto the domain event payload
     */
    @KafkaListener(
            topics = "product-events",
            groupId = "product-service-read-model",
            containerFactory = "productKafkaListenerContainerFactory"
    )
    public void handleProductEvent(ProductDto productDto) {
        log.info("Received product event for id: {}", productDto.getId());

        if (productDto.getId() == null) {
            log.warn("Received product event with null id - ignoring");
            return;
        }

        // Delete event: only id is populated, name is null
        if (productDto.getName() == null) {
            log.info("Processing PRODUCT_DELETED event for id: {}", productDto.getId());
            productReadRepository.deleteById(String.valueOf(productDto.getId()));
            return;
        }

        // Create or Update event: upsert into MongoDB
        log.info("Processing PRODUCT_CREATED/UPDATED event for id: {}", productDto.getId());

        ProductReadModel readModel = productReadRepository
                .findById(String.valueOf(productDto.getId()))
                .orElse(ProductReadModel.builder()
                        .id(String.valueOf(productDto.getId()))
                        .createdAt(LocalDateTime.now())
                        .build());

        readModel.setName(productDto.getName());
        readModel.setDescription(productDto.getDescription());
        readModel.setPrice(productDto.getPrice());
        readModel.setCategory(productDto.getCategory());
        readModel.setQuantity(productDto.getQuantity());
        readModel.setUpdatedAt(LocalDateTime.now());

        productReadRepository.save(readModel);
        log.info("Read model updated for product id: {}", productDto.getId());
    }
}
