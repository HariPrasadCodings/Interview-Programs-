package com.interview.microservices.product.repository;

import com.interview.microservices.product.readmodel.ProductReadModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MongoDB repository for the <b>read model</b>.
 *
 * <p>Used exclusively by {@code ProductQueryService} to serve read
 * requests. All data in this repository is projected from Kafka events
 * produced by the write side.
 */
@Repository
public interface ProductReadRepository extends MongoRepository<ProductReadModel, String> {

    /**
     * Find all products belonging to a given category.
     *
     * @param category the product category
     * @return list of matching products
     */
    List<ProductReadModel> findByCategory(String category);

    /**
     * Search products whose name contains the given text (case-insensitive).
     *
     * @param name search term
     * @return list of matching products
     */
    List<ProductReadModel> findByNameContainingIgnoreCase(String name);
}
