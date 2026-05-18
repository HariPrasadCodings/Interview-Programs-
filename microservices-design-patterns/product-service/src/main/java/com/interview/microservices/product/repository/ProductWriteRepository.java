package com.interview.microservices.product.repository;

import com.interview.microservices.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for the <b>write model</b> (H2).
 *
 * <p>Used exclusively by {@code ProductCommandService} to persist product
 * state changes. Read operations should go through the read-side
 * {@code ProductReadRepository} backed by MongoDB.
 */
@Repository
public interface ProductWriteRepository extends JpaRepository<Product, Long> {
}
