package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.dto.BasketResponse;
import com.peaksoft.gadgetarium2j7.model.dto.ProductResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import com.peaksoft.gadgetarium2j7.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {
    private final BasketService  basketService;


    @PostMapping()
    public ResponseEntity<BasketResponse> addProductToBasket(@RequestParam Long productId, Principal principal) {
        BasketResponse response = basketService.addProductToBasket(productId, principal);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@RequestParam Long productId, Principal principal) {
        basketService.deleteProduct(productId, principal);
        return new ResponseEntity<>("Товар успешно удален из корзины", HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public List<Product> findAll(Principal principal) {
        return basketService.getProductsInBasket(principal);
    }

    @PostMapping("/clearBasket")
    public void clearBasket(Principal principal) {
        basketService.clearBasket(principal);
}

    @GetMapping("/{id}")
    public ProductResponse findById(@Param("id") Long productId, Principal principal) {
        System.out.println("in controller");
        return basketService.findProductById(productId, principal);
    }



}
