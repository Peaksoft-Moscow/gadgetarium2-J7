package com.peaksoft.gadgetarium2j7.repository;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
