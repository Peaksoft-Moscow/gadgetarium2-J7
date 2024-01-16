package com.peaksoft.gadgetarium2j7.repository;

import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.model.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product AS p WHERE " +
            "(:productName IS NULL OR p.name IN :productName) AND " +
            "(:categoryName IS NULL OR p.category.name IN :categoryName) AND " +
            "(:subCategory IS NULL OR p.category.subCategory IN :subCategory) AND " +
            "(:brandName IS NULL OR p.brandName IN :brandName) AND " +
            "(:priceFrom IS NULL OR p.price >= :priceFrom) AND " +
            "(:priceTo IS NULL OR p.price <= :priceTo) AND " +
            "(:color IS NULL OR p.color IN :color) AND " +
            "(:memory IS NULL OR p.memory IN :memory) AND " +
            "(:operationMemory IS NULL OR p.operationMemory IN :operationMemory) AND " +
            "(:operatingSystem IS NULL OR p.operatingSystem IN :operatingSystem) AND " +
            "(:simCard IS NULL OR p.simCard IN :simCard) AND " +
            "(:waterResistance IS NULL OR p.waterResistance = :waterResistance)")
    List<Product> findProductsByCriteria(
            @Param("productName") List<String> productNames,
            @Param("categoryName") List<String> categoryNames,
            @Param("subCategory") List<SubCategory> subCategories,
            @Param("brandName") List<String> brandNames,
            @Param("priceFrom") Double priceFrom,
            @Param("priceTo") Double priceTo,
            @Param("color") List<String> colors,
            @Param("memory") List<Memory> memoryOptions,
            @Param("operationMemory") List<OperationMemory> operationMemories,
            @Param("operatingSystem") List<OperationSystem> operatingSystems,
            @Param("simCard") List<Integer> simCard,
            @Param("waterResistance") WaterResistance waterResistance);

    @Query("SELECT p FROM Product p WHERE p.discount IS NOT NULL")
    List<Product> findProductsByDiscount();

    @Query("SELECT p FROM Product p " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'dateAdded' THEN p.creatDate END DESC, " +
            "CASE WHEN :sortBy = 'priceAsc' THEN p.price END ASC, " +
            "CASE WHEN :sortBy = 'priceDesc' THEN p.price END DESC")
    List<Product> findAllSortedBy(@Param("sortBy") String sortBy);

}


