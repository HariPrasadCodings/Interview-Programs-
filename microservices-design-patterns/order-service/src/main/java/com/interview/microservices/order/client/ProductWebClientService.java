package com.interview.microservices.order.client;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.common.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * PATTERN 3: WebClient (Reactive / Non-Blocking REST Client)
 *
 * How it works:
 *   1. WebClient.Builder is annotated with @LoadBalanced in WebClientConfig,
 *      enabling Eureka-based service discovery (same as RestTemplate).
 *   2. Calls return Mono<T> or Flux<T> (Project Reactor types), which are
 *      lazy - no HTTP request is made until someone subscribes.
 *   3. The calling thread is NOT blocked while waiting for the response;
 *      it can process other requests (event-loop model).
 *
 * Interview Tip:
 *   "WebClient is Spring's recommended replacement for RestTemplate.
 *    It supports both blocking (.block()) and non-blocking (subscribe/Mono)
 *    usage. In a WebFlux application it is fully non-blocking; in a
 *    Servlet-based app (like this one) you typically call .block() at the
 *    boundary, but the underlying I/O is still non-blocking via Netty."
 *
 * Comparison:
 *   - Pros  : Non-blocking I/O, fluent API, backpressure support, future-proof
 *   - Cons  : Steeper learning curve (reactive paradigm), harder to debug
 *   - Best for : High-throughput services, reactive pipelines, streaming
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ProductWebClientService {

    private final WebClient.Builder webClientBuilder;

    private static final String PRODUCT_SERVICE_URL = "http://product-service/api/products";

    /**
     * Fetches a product reactively. Returns Mono<ApiResponse<ProductDto>>.
     *
     * The caller can:
     *   - .block()      : Wait synchronously (servlet context)
     *   - .subscribe()  : Process asynchronously (reactive context)
     *   - chain with .flatMap(), .map(), etc. for reactive pipelines
     */
    public Mono<ApiResponse<ProductDto>> getProduct(Long id) {
        log.info("Fetching product {} via WebClient (reactive)", id);

        return webClientBuilder.build()
                .get()
                .uri(PRODUCT_SERVICE_URL + "/{id}", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<ProductDto>>() {});
    }

    /**
     * Fetches all products reactively.
     */
    public Mono<ApiResponse<List<ProductDto>>> getAllProducts() {
        log.info("Fetching all products via WebClient (reactive)");

        return webClientBuilder.build()
                .get()
                .uri(PRODUCT_SERVICE_URL)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<ProductDto>>>() {});
    }

    /**
     * Demonstrates blocking usage of WebClient for synchronous contexts.
     *
     * Interview Tip:
     *   "Even though we call .block(), the underlying HTTP call uses
     *    Netty's non-blocking I/O. The .block() just bridges from
     *    reactive to imperative at the service boundary."
     */
    public ApiResponse<ProductDto> getProductBlocking(Long id) {
        log.info("Fetching product {} via WebClient (blocking bridge)", id);
        return getProduct(id).block();
    }
}
