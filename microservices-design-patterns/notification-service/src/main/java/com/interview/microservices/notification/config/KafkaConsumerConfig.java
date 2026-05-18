package com.interview.microservices.notification.config;

import com.interview.microservices.common.event.OrderEvent;
import com.interview.microservices.common.event.PaymentEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka consumer configuration for the notification service.
 *
 * <p>Defines two separate {@link ConcurrentKafkaListenerContainerFactory} beans
 * - one for {@link OrderEvent} and one for {@link PaymentEvent} - so each
 * listener can deserialize its specific event type safely.
 *
 * <p>Key configuration decisions:
 * <ul>
 *   <li><b>JsonDeserializer</b> with trusted packages ensures type-safe
 *       deserialization of shared DTO classes from the common-dto module.</li>
 *   <li><b>ErrorHandlingDeserializer</b> wraps the JsonDeserializer to prevent
 *       poison-pill messages from crashing the consumer.</li>
 *   <li><b>earliest auto-offset-reset</b> ensures the service processes events
 *       published before it started (useful for replay and catch-up).</li>
 * </ul>
 *
 * Interview Tip:
 *   "Using a separate ContainerFactory per event type is a clean approach when
 *    consuming from topics with different payload schemas. Alternatively, you
 *    could use a single factory with a type-mapping header, but explicit
 *    factories make the configuration more readable and maintainable."
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    // ======================== Common Properties ========================

    private Map<String, Object> baseConsumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.interview.microservices.common.*");
        return props;
    }

    // ======================== OrderEvent Consumer ========================

    @Bean
    public ConsumerFactory<String, OrderEvent> orderEventConsumerFactory() {
        Map<String, Object> props = baseConsumerProps();
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, OrderEvent.class.getName());
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderEvent>
            orderEventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderEventConsumerFactory());
        return factory;
    }

    // ======================== PaymentEvent Consumer ========================

    @Bean
    public ConsumerFactory<String, PaymentEvent> paymentEventConsumerFactory() {
        Map<String, Object> props = baseConsumerProps();
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, PaymentEvent.class.getName());
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentEvent>
            paymentEventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PaymentEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(paymentEventConsumerFactory());
        return factory;
    }
}
