package com.peaksoft.gadgetarium2j7.repository;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.id = :productId")
    Optional<Product> findById(@Param("productId") Long productId);
}
