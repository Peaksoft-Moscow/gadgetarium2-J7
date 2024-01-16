package com.peaksoft.gadgetarium2j7.repository;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.model.enums.Memory;
import com.peaksoft.gadgetarium2j7.model.enums.OperationSystem;
import com.peaksoft.gadgetarium2j7.model.enums.WaterResistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query("SELECT p FROM Product p " +
            "WHERE (:productName IS NULL OR UPPER(p.name) LIKE UPPER(CONCAT('%', :productName, '%'))) " +
            "AND (:brandName IS NULL OR UPPER(p.brand.name) LIKE UPPER(CONCAT('%', :brandName, '%'))) " +
            "AND (:priceFrom IS NULL OR p.price >= :priceFrom) " +
            "AND (:priceTo IS NULL OR p.price <= :priceTo) " +
            "AND (:color IS NULL OR UPPER(p.color) LIKE UPPER(CONCAT('%', :color, '%'))) " +
            "AND (:memory IS NULL OR p.memory = :memory) " +
            "AND (:operatingSystem IS NULL OR p.operatingSystem = :operatingSystem) " +
            "AND (:waterproof IS NULL OR p.waterResistance = :waterproof) " +
            "AND (:categoryName IS NULL OR UPPER(p.category.name) LIKE UPPER(CONCAT('%', :categoryName, '%'))) " +
            "AND (:subCategory IS NULL OR UPPER(p.category.subCategory) LIKE UPPER(CONCAT('%', :subCategory, '%')))")
    List<Product> searchProducts(@Param("productName") String productName,
                                 @Param("brandName") String brandName,
                                 @Param("priceFrom") Double priceFrom,
                                 @Param("priceTo") Double priceTo,
                                 @Param("color") String color,
                                 @Param("memory") Memory memory,
                                 @Param("operatingSystem") OperationSystem operationSystem,
                                 @Param("waterproof") WaterResistance waterResistance,
                                 @Param("categoryName") String categoryName,
                                 @Param("subCategory") String subCategory);
    @Query("SELECT p FROM Product p " +
            "WHERE (:productName IS NULL OR UPPER(p.name) LIKE UPPER(CONCAT('%', :productName, '%'))) " +
            "AND (:categoryName IS NULL OR UPPER(p.category.name) IN :categoryNames) " +
            "AND (:brandName IS NULL OR UPPER(p.brand.name) IN :brandNames) " +
            "AND (:priceFrom IS NULL OR p.price >= :priceFrom) " +
            "AND (:priceTo IS NULL OR p.price <= :priceTo) " +
            "AND (:color IS NULL OR UPPER(p.color) IN :colors) " +
            "AND (:memory IS NULL OR p.memory IN :memoryOptions) " +
            "AND (:operatingSystem IS NULL OR UPPER(p.operatingSystem) IN :operatingSystems) " +
            "AND (:waterResistance IS NULL OR p.waterResistance = :waterResistance)")
    List<Product> searchProducts2(
            @Param("productName") String productName,
            @Param("categoryNames") Set<String> categoryNames,
            @Param("brandNames") Set<String> brandNames,
            @Param("priceFrom") Double priceFrom,
            @Param("priceTo") Double priceTo,
            @Param("colors") Set<String> colors,
            @Param("memoryOptions") Set<Memory> memoryOptions,
            @Param("operatingSystems") Set<String> operatingSystems,
            @Param("waterResistance") WaterResistance waterResistance);
}

