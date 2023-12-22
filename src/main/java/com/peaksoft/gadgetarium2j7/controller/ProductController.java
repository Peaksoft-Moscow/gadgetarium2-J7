package com.peaksoft.gadgetarium2j7.controller;
import com.peaksoft.gadgetarium2j7.entity.dto.ProductRequest;
import com.peaksoft.gadgetarium2j7.entity.dto.ProductResponse;
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
    @PostMapping("/create")
    public ResponseEntity<ProductResponse> add(@RequestBody ProductRequest productRequest){
        System.out.println("controller");
        ProductResponse response = productService.create(productRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ProductResponse update(@PathVariable("id")Long id, @RequestBody ProductRequest productRequest){
        return productService.updateProduct(id, productRequest);}
   @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")Long id){
        productService.delete(id);
        return "Delete product with id:" + id +"successfully delete";
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
