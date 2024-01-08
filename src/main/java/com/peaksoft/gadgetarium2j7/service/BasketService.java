package com.peaksoft.gadgetarium2j7.service;

import com.peaksoft.gadgetarium2j7.mapper.BasketMapper;
import com.peaksoft.gadgetarium2j7.model.dto.BasketResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Basket;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.model.entities.ProductAmount;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.repository.BasketRepository;
import com.peaksoft.gadgetarium2j7.repository.ProductRepository;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public BasketResponse addProductToBasket(Long productId, String email) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Товар не найден"));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        if (product.getInStock() <= 0) {
            throw new EntityNotFoundException("Этого товара нет в наличии!");
        }

        // Проверяем наличие товара в корзине пользователя
        Optional<ProductAmount> optionalProductAmount = user.getBasket().getProductAmountList().stream()
                .filter(pa -> pa.getProduct().equals(product))
                .findFirst();
        if (optionalProductAmount.isPresent()) {
            // Если товар уже есть в корзине, увеличиваем количество и общую цену корзины
            ProductAmount productAmount = optionalProductAmount.get();
            productAmount.setAmount(productAmount.getAmount() + 1);
            productAmount.setTotalPrice(productAmount.getTotalPrice() + product.getPrice());
        } else {
            if (product.getInStock() > 0) {
                // Если товара нет в корзине,но есть в наличии создаем новый объект ProductAmount
                ProductAmount productAmount = new ProductAmount();
                productAmount.setProduct(product);
                productAmount.setAmount(1);
                productAmount.setPrice(product.getPrice());
                productAmount.setTotalPrice(product.getTotalPrice());
                productAmount.setBasket(user.getBasket());
                user.getBasket().getProductAmountList().add(productAmount);
            }
        }

        // Увеличиваем общую цену корзины на цену добавленного товара
        user.getBasket().setTotal(user.getBasket().getTotal() + product.getPrice());

        // Сохраняем изменения
        basketRepository.save(user.getBasket());
        productRepository.save(product);

        return basketMapper.mapToResponse(user.getBasket());
    }

    public void deleteProduct(Long productId, String email) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Продукт не найден"));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        // Поиск продукта в корзине пользователя
        Optional<ProductAmount> optionalProductAmount = user.getBasket().getProductAmountList().stream()
                .filter(pa -> pa.getProduct().equals(product))
                .findFirst();

        if (optionalProductAmount.isPresent()) {
            // Удаление продукта из корзины, сохранение изменений
            ProductAmount productAmount = optionalProductAmount.get();
            user.getBasket().getProductAmountList().remove(productAmount);
            product.getProductAmounts().remove(productAmount);
            basketRepository.save(user.getBasket());
            productRepository.save(product);
        } else {
            throw new EntityNotFoundException("Продукт не найден в корзине пользователя");
        }
    }

    public List<Product> getProductsInBasket(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Basket cart = user.getBasket();
        if (cart != null && !cart.getProducts().isEmpty()) {
            return new ArrayList<>(cart.getProducts());
        } else {
            throw new RuntimeException("Ваша корзина пуста, но вы можете ее наполнить");
        }
    }

    public void clearBasket(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Basket basket = user.getBasket();
        if (basket != null && !basket.getProductAmountList().isEmpty()) {
            basket.getProductAmountList().clear(); // Очищаем список продуктов в корзине
            basket.setTotal(0); // Устанавливаем общую стоимость корзины в 0
            basketRepository.save(basket); // Сохраняем изменения в корзине
        } else {
            throw new RuntimeException("Ваша корзина уже пуста");
        }
    }

    public Product getProductFromBasket(Long productId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        // Поиск продукта в корзине пользователя
        Optional<ProductAmount> optionalProductAmount = user.getBasket().getProductAmountList().stream()
                .filter(pa -> pa.getProduct().getId().equals(productId))
                .findFirst();

        if (optionalProductAmount.isPresent()) {
            return optionalProductAmount.get().getProduct();
        } else {
            throw new EntityNotFoundException("Продукт не найден в корзине пользователя");
        }

    }
}




