package com.interview.microservices.inventory.config;

import com.interview.microservices.common.event.InventoryEvent;
import com.interview.microservices.common.event.OrderEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka configuration for both consumer and producer.
 *
 * <p><b>Interview note (Async Messaging pattern):</b>
 * <ul>
 *   <li><b>Consumer</b> - listens to {@code order-events} topic and
 *       deserializes incoming messages into {@link OrderEvent} objects</li>
 *   <li><b>Producer</b> - publishes {@link InventoryEvent} messages to
 *       the {@code inventory-events} topic</li>
 * </ul>
 *
 * <p>The {@link JsonDeserializer} is configured with trusted packages
 * so that it can deserialize events from the shared {@code common-dto}
 * module.
 */
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    // ======================== Consumer Configuration ========================

    /**
     * Consumer factory for {@link OrderEvent} messages.
     * Uses a type-aware JSON deserializer pointed at the shared DTO package.
     */
    @Bean
    public ConsumerFactory<String, OrderEvent> orderEventConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        JsonDeserializer<OrderEvent> deserializer = new JsonDeserializer<>(OrderEvent.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer
        );
    }

    /**
     * Listener container factory wired to the order-event consumer factory.
     * Referenced by name in {@code @KafkaListener(containerFactory = ...)}.
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderEvent>
            orderEventListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderEventConsumerFactory());
        return factory;
    }

    // ======================== Producer Configuration ========================

    /**
     * Producer factory for {@link InventoryEvent} messages.
     */
    @Bean
    public ProducerFactory<String, InventoryEvent> inventoryEventProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    /**
     * Kafka template for sending {@link InventoryEvent} messages.
     */
    @Bean
    public KafkaTemplate<String, InventoryEvent> kafkaTemplate() {
        return new KafkaTemplate<>(inventoryEventProducerFactory());
    }
}
