package com.interview.microservices.product.config;

import com.interview.microservices.common.dto.ProductDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka consumer configuration for reading product domain events.
 *
 * <p>The consumer deserialises JSON payloads into {@link ProductDto} objects.
 * An {@link ErrorHandlingDeserializer} wraps the JSON deserialiser to
 * gracefully handle poison pills (malformed messages) instead of crashing
 * the consumer loop.
 *
 * <h3>CQRS relevance</h3>
 * <p>This consumer feeds the read-side projection. The
 * {@code ProductEventHandler} uses the container factory defined here
 * to listen for events on the {@code product-events} topic and update
 * the MongoDB read model accordingly.
 */
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    /**
     * Creates a {@link ConsumerFactory} configured with String keys and
     * JSON-deserialised {@link ProductDto} values.
     */
    @Bean
    public ConsumerFactory<String, ProductDto> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Key deserializer
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);

        // Value deserializer - JSON with trusted packages
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.interview.microservices.*");
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, ProductDto.class.getName());
        configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    /**
     * Creates the {@link ConcurrentKafkaListenerContainerFactory} used by
     * {@code @KafkaListener} annotated methods.
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductDto>
            productKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ProductDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
