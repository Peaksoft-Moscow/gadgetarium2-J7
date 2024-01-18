package com.peaksoft.gadgetarium2j7.repository;
import com.peaksoft.gadgetarium2j7.model.entities.Category;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.model.enums.OperationMemory;
import com.peaksoft.gadgetarium2j7.model.enums.OperationSystem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query("select  p from  Product  p join  p.users u where p.id=:id")
    Product getAllByProductId (@Param("id") Long id );
    @Query("select  p from Product p join p.users u where upper(p.name) like:name and (p.price between :min and :max)" +
            " and upper(p.color) like:color and upper(p.operationMemory) =:memory and upper(p.operatingSystem) =:os")
    List <Product> getAllProductByFilter (@Param("name") String category, @Param("min") double minPrice,
                                          @Param("max") double maxPrice, @Param("color") String color,
                                          @Param("memory") OperationMemory operationMemory,
                                          @Param("os")OperationSystem operationSystem, Pageable pageable);
    @Query("select p from Product p join p.users u where p.category.name = :category and u.id=:id")
    List<Product>getProductByCategory(@Param("id")Long id,@Param("category")String category);


}


