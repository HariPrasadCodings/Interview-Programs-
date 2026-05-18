package com.interview.microservices.notification.repository;

import com.interview.microservices.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for {@link Notification} entities.
 *
 * <p>Spring Data auto-generates the implementation at runtime by parsing
 * the method names and deriving the corresponding SQL queries.
 *
 * Interview Tip:
 *   "Spring Data JPA uses a naming convention (findBy<Property>) to derive
 *    queries automatically. For complex queries you can use @Query with JPQL
 *    or native SQL. The repository abstraction eliminates boilerplate DAO code."
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * Finds all notifications for a given user, ordered by creation date descending.
     *
     * @param userId the target user's ID
     * @return list of notifications, most recent first
     */
    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);

    /**
     * Finds all notifications of a specific type (e.g., ORDER_CONFIRMATION).
     *
     * @param type the notification type
     * @return list of matching notifications
     */
    List<Notification> findByType(String type);
}
