package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.dto.*;
import com.peaksoft.gadgetarium2j7.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/main-page")
    public SpecialProductsResponse specialProducts(){
        return productService.getSpecialProducts();
    }
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


}
