package com.interview.microservices.order.client;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.common.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * PATTERN 1: Feign Client (Declarative REST Client)
 *
 * How it works:
 *   1. @FeignClient("product-service") tells Spring Cloud to create a proxy
 *      that resolves "product-service" via Eureka service registry.
 *   2. At runtime, calling getProduct(1L) generates an HTTP GET request to
 *      http://product-service/api/products/1 (load-balanced via Eureka).
 *   3. The JSON response is automatically deserialized into ApiResponse<ProductDto>.
 *
 * Interview Tip:
 *   "Feign is the preferred approach in Spring Cloud because it is
 *    declarative (just an interface), integrates natively with Eureka
 *    and Resilience4j, and reduces boilerplate compared to RestTemplate.
 *    Under the hood, it uses reflection to build HTTP requests from
 *    the annotated method signatures."
 *
 * Comparison with other approaches:
 *   - vs RestTemplate : Less boilerplate, no manual URL construction
 *   - vs WebClient    : Synchronous/blocking (simpler mental model),
 *                        but lacks non-blocking reactive support
 */
@FeignClient(
        name = "product-service",
        path = "/api/products"
)
public interface ProductFeignClient {

    @GetMapping("/{id}")
    ApiResponse<ProductDto> getProduct(@PathVariable("id") Long id);

    @GetMapping
    ApiResponse<java.util.List<ProductDto>> getAllProducts();
}
