package com.interview.microservices.order.client;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.common.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * PATTERN 2: RestTemplate (Imperative REST Client)
 *
 * How it works:
 *   1. The RestTemplate bean is annotated with @LoadBalanced in
 *      RestTemplateConfig, which registers a LoadBalancerInterceptor.
 *   2. When we use "http://product-service/..." as the URL, the interceptor
 *      resolves "product-service" to an actual host:port via Eureka.
 *   3. The interceptor also performs client-side load balancing (round-robin
 *      by default) across multiple instances of product-service.
 *
 * Interview Tip:
 *   "RestTemplate is the traditional, synchronous HTTP client in Spring.
 *    While it still works, Spring officially recommends migrating to
 *    WebClient (reactive) or RestClient (Spring 6.1+). RestTemplate
 *    blocks the calling thread while waiting for a response."
 *
 * Comparison:
 *   - Pros  : Simple, well-understood, wide community support
 *   - Cons  : Blocking I/O, verbose, officially in maintenance mode
 *   - Best for : Legacy codebases, simple synchronous calls
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ProductRestTemplateClient {

    private final RestTemplate restTemplate;

    private static final String PRODUCT_SERVICE_URL = "http://product-service/api/products";

    /**
     * Fetches a single product by ID using RestTemplate.
     *
     * Note: We use ParameterizedTypeReference to preserve generic type
     * information that would otherwise be lost due to Java type erasure.
     */
    public ApiResponse<ProductDto> getProduct(Long id) {
        log.info("Fetching product {} via RestTemplate", id);

        ResponseEntity<ApiResponse<ProductDto>> response = restTemplate.exchange(
                PRODUCT_SERVICE_URL + "/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {},
                id
        );

        return response.getBody();
    }

    /**
     * Fetches all products using RestTemplate.
     */
    public ApiResponse<java.util.List<ProductDto>> getAllProducts() {
        log.info("Fetching all products via RestTemplate");

        ResponseEntity<ApiResponse<java.util.List<ProductDto>>> response = restTemplate.exchange(
                PRODUCT_SERVICE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }
}
