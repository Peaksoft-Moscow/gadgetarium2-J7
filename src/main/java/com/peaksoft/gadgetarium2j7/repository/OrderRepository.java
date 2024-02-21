package com.peaksoft.gadgetarium2j7.repository;

import com.peaksoft.gadgetarium2j7.model.entities.Basket;
import com.peaksoft.gadgetarium2j7.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT SUM(o.total) AS totalAmount, o.deliveryAddress AS deliveryAddress, o.paymentMethod AS paymentMethod " +
            "FROM Order o " +
            "JOIN o.user u " +
            "WHERE u.email = :email " +
            "GROUP BY o.deliveryAddress, o.paymentMethod")
    List<Object[]> getTotalAmountAndDeliveryInfoByEmail(@Param("email") String email);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.user.email = :email AND o.orderDate BETWEEN :startDate AND :endDate")
    int countOrderByUserAndDateRange(String email, LocalDateTime startDate, LocalDateTime endDate);
}