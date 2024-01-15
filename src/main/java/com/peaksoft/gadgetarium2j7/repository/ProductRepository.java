package com.peaksoft.gadgetarium2j7.repository;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.model.enums.Memory;
import com.peaksoft.gadgetarium2j7.model.enums.OperationSystem;
import com.peaksoft.gadgetarium2j7.model.enums.WaterResistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p " +
            "WHERE UPPER(p.name) LIKE UPPER(CONCAT('%', :productName, '%')) " +
            "AND UPPER(p.brandName) LIKE UPPER(CONCAT('%', :brandName, '%')) " +
            "AND p.price BETWEEN :priceFrom AND :priceTo " +
            "AND UPPER(p.color) LIKE UPPER(CONCAT('%', :color, '%')) " +
            "AND p.memory = :memoryType " +
            "AND p.operatingSystem = :operatingSystem " +
            "AND p.waterResistance = :waterproof")
    List<Product> findProducts(@Param("productName") String productName,
                               @Param("brandName") String brandName,
                               @Param("priceFrom") double priceFrom,
                               @Param("priceTo") double priceTo,
                               @Param("color") String color,
                               @Param("memoryType") Memory memoryType,
                               @Param("operatingSystem")OperationSystem operationSystem,
                               @Param("waterproof") WaterResistance waterResistance);
}


