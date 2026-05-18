package com.interview.microservices.inventory.repository;

import com.interview.microservices.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for {@link Inventory} entities.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    /**
     * Find inventory record by product ID.
     *
     * @param productId the unique product identifier
     * @return an Optional containing the inventory if found
     */
    Optional<Inventory> findByProductId(Long productId);
}
