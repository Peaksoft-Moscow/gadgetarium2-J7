package com.peaksoft.gadgetarium2j7.repository;

import com.peaksoft.gadgetarium2j7.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}