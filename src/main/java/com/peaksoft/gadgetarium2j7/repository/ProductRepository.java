package com.peaksoft.gadgetarium2j7.repository;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p join  p.users u ")
    List<Product> getAllProduct();
    @Query("select p from Product  p join  p.users u where  u.id=:userId")
    List<Product>getProductByUser(@Param("userId") Long userId);

    @Query("select  p from  Product  p join  p.users u where p.id=:id")
    Product getAllByProductId (@Param("id") Long id );

    @Query("select p from Product p join p.users u where p.category.name =:category " +
            "and u.id=:id")
    List<Product>getProductByCategory(@Param("id")Long id,@Param("category")String category);

}


