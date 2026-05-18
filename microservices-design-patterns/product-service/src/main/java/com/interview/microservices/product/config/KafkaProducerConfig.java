package com.interview.microservices.product.config;

import com.interview.microservices.common.dto.ProductDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka producer configuration for publishing product domain events.
 *
 * <p>The producer serialises the key as a plain {@link String} (product id)
 * and the value as JSON ({@link ProductDto}). This allows consumers to
 * deserialise the event payload without coupling to the entity class.
 *
 * <h3>CQRS relevance</h3>
 * <p>The producer is used exclusively by {@code ProductCommandService} on
 * the write side. After every successful database mutation, a domain event
 * is pushed to the {@code product-events} topic. The read-side consumer
 * ({@code ProductEventHandler}) picks it up and updates MongoDB.
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Creates a {@link ProducerFactory} configured with String keys and
     * JSON-serialised {@link ProductDto} values.
     */
    @Bean
    public ProducerFactory<String, ProductDto> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        // Ensure JSON type headers are added so the consumer can deserialise correctly
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Creates the {@link KafkaTemplate} bean used to send product events.
     */
    @Bean
    public KafkaTemplate<String, ProductDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
