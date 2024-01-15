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

    public BasketResponse addProductToBasket(Long productId, Principal principal) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Товар не найден"));
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        if (product.getInStock() <= 0) {
            throw new EntityNotFoundException("Этого товара нет в наличии!");
        }
        //продукту userдин корзинасынан издоо
        Map<Product, Integer> productAmountMap = getProductAmountMap(user.getBasket());
        if (productAmountMap.containsKey(product)) {
            int amount = productAmountMap.get(product);
            productAmountMap.put(product, amount + 1);
        } else {
            if (product.getInStock() > 0) {
                productAmountMap.put(product, 1);
            }
        }
        Basket basket= user.getBasket();
        basket.setTotal(user.getBasket().getTotal() + product.getPrice());
        List<Product>products=new ArrayList<>();
        products.add(product);
        basket.setProducts(products);
        basket.setUser(user);
        basketRepository.save(basket);
//        productRepository.save(product);


        return basketMapper.mapToResponse(basket);
    }

    private Map<Product, Integer> getProductAmountMap(Basket basket) {
        Map<Product, Integer> productAmountMap = new HashMap<>();
        for (Product product : basket.getProducts()) {
            if (basket.getProducts().isEmpty()) {
                System.out.println("jhfjdhsgkjhfkj");
                if (productAmountMap.containsKey(product)) {
                    productAmountMap.put(product, productAmountMap.get(product) + 1);
                } else {
                    productAmountMap.put(product, 1);
                }
            }
        }
        return productAmountMap;
    }


    public void deleteProduct(Long productId, Principal principal) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Продукт не найден"));
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (user.getBasket().getProducts().contains(product)) {
            int productCount = Collections.frequency(user.getBasket().getProducts(), product);
            user.getBasket().getProducts().removeIf(p -> p.getId().equals(productId));
            user.getBasket().setTotal(user.getBasket().getTotal() - product.getPrice() * productCount);
            basketRepository.save(user.getBasket());
        }
    }

    public void clearBasket(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Basket basket = user.getBasket();
        if (basket != null) {
            basketRepository.clearBasketForUser(user.getId());
            basket.setTotal(0);
            basketRepository.save(basket);
        } else {
            throw new RuntimeException("Ваша корзина уже пуста");
        }
    }

    public List<Product> getProductsInBasket(Principal principal) {
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
        System.out.println("jjjjj");
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Basket basket = user.getBasket();
        if (basket != null && !basket.getProducts().isEmpty()) {
            for (Product product : basket.getProducts()) {
                if (product.getId().equals(productId)) {
                    System.out.println("kjjjkljkl");
                    return  productMapper.mapToResponse(product);
                }
            }
        }
        throw new EntityNotFoundException("Продукт не найден в корзине");
    }


//
//    public void clearBasket(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
//
//        Basket basket = user.getBasket();
//        if (basket != null && !basket.getProductAmountList().isEmpty()) {
//            basket.getProductAmountList().clear();
//            basket.setTotal(0);
//            basketRepository.save(basket);
//        } else {
//            throw new RuntimeException("Ваша корзина уже пуста");
//        }
//    }
//
//    public Product getProductFromBasket(Long productId, String email) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
//
//        //продукту userдин корзинасынан издоо
//        Optional<ProductAmount> optionalProductAmount = user.getBasket().getProductAmountList().stream()
//                .filter(pa -> pa.getProduct().getId().equals(productId))
//                .findFirst();
//
//        if (optionalProductAmount.isPresent()) {
//            return optionalProductAmount.get().getProduct();
//        } else {
//            throw new EntityNotFoundException("Продукт не найден в корзине пользователя");
//        }
//

//    public BasketResponse addProductToBasket(Long productId, String email) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new EntityNotFoundException("Товар не найден"));
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
//        if (product.getInStock() <= 0) {
//            throw new EntityNotFoundException("Этого товара нет в наличии!");
//        }
//        //продукту userдин корзинасынан издоо
//        Optional<ProductAmount> optionalProductAmount = user.getBasket().getProductAmountList().stream()
//                .filter(pa -> pa.getProduct().equals(product))
//                .findFirst();
//        if (optionalProductAmount.isPresent()) {
//            ProductAmount productAmount = optionalProductAmount.get();
//            productAmount.setAmount(productAmount.getAmount() + 1);
//            productAmount.setTotalPrice(productAmount.getTotalPrice() + product.getPrice());
//        } else {
//            if (product.getInStock() > 0) {
//                ProductAmount productAmount = new ProductAmount();
//                productAmount.setProduct(product);
//                productAmount.setAmount(1);
//                productAmount.setPrice(product.getPrice());
//                productAmount.setTotalPrice(product.getTotalPrice());
//                productAmount.setBasket(user.getBasket());
//                user.getBasket().getProductAmountList().add(productAmount);
//            }
//        }
//
//        user.getBasket().setTotal(user.getBasket().getTotal() + product.getPrice());
//
//        basketRepository.save(user.getBasket());
//        productRepository.save(product);
//
//        return basketMapper.mapToResponse(user.getBasket());
//    }
//    }
}




