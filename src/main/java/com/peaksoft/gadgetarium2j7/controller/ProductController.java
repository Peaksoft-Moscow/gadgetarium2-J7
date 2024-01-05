package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.dto.*;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    @PostMapping()
    public ResponseEntity<ProductResponse> add(@RequestBody ProductRequest productRequest){
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

   @GetMapping()
    public List<ProductResponse> getAllProduct(){
        return productService.getAll();
   }

   @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id){
        return productService.getProductById(id);
   }
   @PutMapping("/compare/{id}") // этот метод сравнивает продуктa.
    public List<ProductResponse> compare_product(@PathVariable("id") Long id, Principal principal){
        return  productService.compare_product(id,principal);
   }
    @GetMapping("/search-productbyfilter")
    public List<ProductResponse> searchAndPaginationProduct(@RequestParam String category,
                                                                @RequestParam double min_price,
                                                                @RequestParam double max_price,@RequestParam String color,
                                                                @RequestParam String operationMemory,
                                                                @RequestParam String operationSystem, @RequestParam int page,
                                                                @RequestParam int size,Principal principal) {
        return productService.searchAndPaginationProduct(category, min_price, max_price, color, operationMemory,
                operationSystem, page, size,principal);
    }
    @DeleteMapping("/delete-products")
    public String deleteProductInCompare(Principal principal){
        productService.deleteProductInCompare(principal);
        return " Successful delete! ";
    }
    @GetMapping("/get-all-products-by-category")
    public List<ProductResponse> getAllProductByCategory(@RequestParam("category") String category,@RequestParam(value = "difference",required = false)boolean difference, Principal principal){
        return productService.getProductByCategory(category,difference,principal);
    }

}
