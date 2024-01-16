package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.dto.*;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.model.enums.*;
import com.peaksoft.gadgetarium2j7.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponse> add(@RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.create(productRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/setDescription/{id}")
    public SetDescriptionResponse setDescription(@PathVariable("id") Long id, @RequestBody SetDescription setDescription) {
        return productService.setDescription(id, setDescription);
    }

    @PatchMapping("/setPriceAndQuantity/{id}")
    public SetPriceAndQuantityResponse setPriceAndQuantity(@PathVariable("id") Long id, @RequestBody SetPriceAndQuantity setPriceAndQuantity) {
        return productService.setPriceAndQuantity(id, setPriceAndQuantity);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return "Delete product with id:" + id + " successfully delete";
    }

    @GetMapping()
    public List<ProductResponse> getAllProduct() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> list(
            @RequestParam(value = "productName", required = false) List<String> productNames,
            @RequestParam(value = "categoryName", required = false) List<String> categoryNames,
            @RequestParam(value = "subCategory", required = false) List<SubCategory> subCategories,
            @RequestParam(value = "brandName", required = false) List<String> brandNames,
            @RequestParam(value = "priceFrom", required = false) Double priceFrom,
            @RequestParam(value = "priceTo", required = false) Double priceTo,
            @RequestParam(value = "color", required = false) List<String> colors,
            @RequestParam(value = "memory", required = false) List<Memory> memoryOptions,
            @RequestParam(value = "operationMemory", required = false) List<OperationMemory> operationMemories,
            @RequestParam(value = "operatingSystem", required = false) List<OperationSystem> operatingSystems,
            @RequestParam(value = "simCard", required = false) List<Integer> simCard,
            @RequestParam(value = "waterResistance", required = false) WaterResistance waterResistance) {

        List<Product> products = productRepository.findProductsByCriteria(
                productNames, categoryNames, subCategories, brandNames,
                priceFrom, priceTo, colors, memoryOptions, operationMemories, operatingSystems, simCard, waterResistance);

        if (products.isEmpty()) {
            return new ResponseEntity<>("No products matching the criteria", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
    }
    @GetMapping("/discount")
    public ResponseEntity<List<Product>> getAllProductsWithDiscount() {
        List<Product> products = productService.getAllProductsWithDiscount();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/sorted")
    public ResponseEntity<List<Product>> getSortedProducts(@RequestParam(value = "sortBy") String sortBy) {
        List<Product> sortedProducts = productService.findAllSortedBy(sortBy);

        if (sortedProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(sortedProducts, HttpStatus.OK);
    }
    @GetMapping("/resetFilters")
    public ResponseEntity<List<Product>> resetFilters() {
        List<Product> allProducts = productRepository.findAll();
        return new ResponseEntity<>(allProducts, HttpStatus.ACCEPTED);
    }

}
