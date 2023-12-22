package com.peaksoft.gadgetarium2j7.repository;

import com.peaksoft.gadgetarium2j7.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query("SELECT g FROM Brand g WHERE g.name = :name")
    Optional<Brand> findByName(@Param("name") String name);
}
