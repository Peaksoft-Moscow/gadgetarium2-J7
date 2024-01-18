package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.dto.*;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    @PostMapping("/add-product")
    public ResponseEntity<ProductResponse> add(@RequestBody ProductRequest productRequest){
        System.out.println("controller before product ");
        ProductResponse response = productService.create(productRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/setDescription/{id}")
    public SetDescriptionResponse setDescription(@PathVariable("id")Long id, @RequestBody SetDescription setDescription){
        return productService.setDescription(id, setDescription);
    }

    @PatchMapping("/setPriceAndQuantity/{id}")
    public SetPriceAndQuantityResponse setPriceAndQuantity(@PathVariable("id")Long id, @RequestBody SetPriceAndQuantity setPriceAndQuantity){
        return productService.setPriceAndQuantity(id, setPriceAndQuantity);
    }

   @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")Long id){
        productService.delete(id);
        return "Delete product with id:" + id +" successfully delete";
   }

   @GetMapping("/get-all")
    public List<ProductResponse> getAllProduct(){
        return productService.getAll();
   }

   @GetMapping("/search-product/{id}")
    public ProductResponse findById(@PathVariable Long id){
        return productService.getProductById(id);
   }
   @PutMapping("/compare-product/{id}")
   @Operation(description = "Добавление продукта в таблицу сравнение ( compare_product ).")
    public String compare_product(@PathVariable("id") Long id, Principal principal){
       System.out.println(("product controller "));
         productService.compare_product(id,principal);
         return " Sucsess add! ";
   }
    @DeleteMapping("/delete")
    @Operation(description = "Очистить все продукты из таблицы сравнение( compare_product ). ")
    public String deleteProductInCompare(Principal principal){
        productService.deleteProductInCompare(principal);
        return " Successful delete! ";
    }
    @GetMapping("/get-all-products-by-category")
    @Operation(description = "Получение продукта по категориям из таблицы сравнение( compare_product ). ")
    public List<ProductResponse> getAllProductByCategory(
                                    @RequestParam("category") String category,
                                    @RequestParam(value = "difference",required = false)boolean difference,
                                    Principal principal){
        return productService.getProductByCategory(category, difference,principal);
    }



}
