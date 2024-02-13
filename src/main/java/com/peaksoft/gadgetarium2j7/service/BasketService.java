package com.peaksoft.gadgetarium2j7.service;

import com.peaksoft.gadgetarium2j7.mapper.BasketMapper;
import com.peaksoft.gadgetarium2j7.mapper.ProductMapper;
import com.peaksoft.gadgetarium2j7.model.dto.BasketResponse;
import com.peaksoft.gadgetarium2j7.model.dto.ProductResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Basket;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.repository.BasketRepository;
import com.peaksoft.gadgetarium2j7.repository.ProductRepository;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private  final ProductMapper productMapper;


    @Transactional
    public BasketResponse addProductToBasket(Long productId, Principal principal) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Товар не найден"));
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (product.getQuantity()<= 0) {
            throw new EntityNotFoundException("Этого товара нет в наличии!");
        }

        Basket basket = basketRepository.findBasketByUserId(user.getId())
                .orElseGet(() -> {
                    Basket newBasket = new Basket();
                    newBasket.setUser(user);
                    return basketRepository.save(newBasket);
                });

        Map<Product, Integer> productAmountMap = getProductAmountMap(basket);
        if (productAmountMap.containsKey(product)) {
            int amount = productAmountMap.get(product);
            productAmountMap.put(product, amount + 1);
        } else {
            productAmountMap.put(product, 1);
        }
        basket.setQuantity(product.getQuantity());
        basket.setAmount(product.getTotalPrice() % product.getQuantity());
        basket.setTotal(basket.getTotal() + product.getPrice());
        List<Product> products = new ArrayList<>(basket.getProducts());
        products.add(product);
        product.setBaskets(Collections.singletonList(basket));
        basket.setProducts(products);

        basketRepository.save(basket);

        return basketMapper.mapToResponse(basket);
    }

    private Map<Product, Integer> getProductAmountMap(Basket basket) {
        Map<Product, Integer> productAmountMap = new HashMap<>();
        for (Product product : basket.getProducts()) {
            if (productAmountMap.containsKey(product)) {
                productAmountMap.put(product, productAmountMap.get(product) + 1);
            } else {
                productAmountMap.put(product, 1);
            }
        }
        return productAmountMap;
    }

    @Transactional
    public void deleteProduct(Long productId, Principal principal) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (user.getBasket().getProducts().contains(product)) {
            int productCount = Collections.frequency(user.getBasket().getProducts(), product);
            user.getBasket().getProducts().removeIf(p -> p.getId().equals(productId));
            user.getBasket().setTotal(user.getBasket().getTotal() - product.getPrice() * productCount);

            userRepository.save(user);
        }
    }

    @Transactional
    public void clearBasket(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Basket basket = user.getBasket();
        if (basket != null) {
            basket.getProducts().clear();
            basket.setTotal(0);
            basketRepository.save(basket);
        } else {
            throw new RuntimeException("Ваша корзина уже пуста");
        }
    }

    public List<Product> findAll(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Basket cart = user.getBasket();
        if (cart != null && !cart.getProducts().isEmpty()) {

            return new ArrayList<>(cart.getProducts());
        } else {
            throw new RuntimeException("Ваша корзина пуста, но вы можете ее наполнить");
        }
    }

    public ProductResponse findProductById(Long productId, Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        Basket basket = user.getBasket();
        if (basket != null && !basket.getProducts().isEmpty()) {
            for (Product product : basket.getProducts()) {
                if (product.getId().equals(productId)) {
                    return productMapper.mapToResponse(product);
                }
            }
        }
        throw new EntityNotFoundException("Продукт не найден в корзине пользователя");
    }
}




