package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.dto.BasketResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {
    private final BasketService  basketService;


    @PostMapping("/addProduct")
    public ResponseEntity<BasketResponse> addProductToBasket(@RequestParam Long productId, @RequestParam String email) {
        BasketResponse response = basketService.addProductToBasket(productId, email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<String> deleteProduct(@RequestParam Long productId, @RequestParam String email) {
        basketService.deleteProduct(productId, email);
        return new ResponseEntity<>("Товар успешно удален из корзины", HttpStatus.OK);
    }

    @GetMapping("/getProductsInBasket")
    public List<Product> getProductsInBasket(@RequestParam Long userId) {
        return basketService.getProductsInBasket(userId);
    }

    @PostMapping("/clearBasket")
    public void clearBasket(@RequestParam Long userId) {
        basketService.clearBasket(userId);
}

//    @GetMapping("/product/{id}")
//    public ResponseEntity<Product> getProductFromBasket(@PathVariable Long id, @AuthenticationPrincipal User userDetails) {
//        Product product = basketService.getProductFromBasket(id, userDetails.getEmail());
//        return ResponseEntity.ok(product);
//    }


}
