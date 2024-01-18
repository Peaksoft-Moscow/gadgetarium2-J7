package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.dto.BasketResponse;
import com.peaksoft.gadgetarium2j7.model.dto.ProductResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {
    private final BasketService basketService;


    @PostMapping("/{id}")
    public ResponseEntity<BasketResponse> addProductToBasket(@PathVariable("id") Long productId, Principal principal) {
        BasketResponse response = basketService.addProductToBasket(productId, principal);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long productId, Principal principal) {
        basketService.deleteProduct(productId, principal);
        return ResponseEntity.ok("Product successfully removed from basket");
    }

    @GetMapping("/findAll")
    public List<Product> findAll(Principal principal) {
        return basketService.findAll(principal);
    }


    @PostMapping("/clearBasket")
    public void clearBasket(Principal principal) {
        basketService.clearBasket(principal);
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable("id") Long productId, Principal principal) {
        return basketService.findProductById(productId, principal);
    }

}
