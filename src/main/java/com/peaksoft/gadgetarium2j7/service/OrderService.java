package com.peaksoft.gadgetarium2j7.service;

import com.peaksoft.gadgetarium2j7.exception.EntityNotFoundException;
import com.peaksoft.gadgetarium2j7.mapper.BasketMapper;
import com.peaksoft.gadgetarium2j7.model.dto.BasketResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Basket;
import com.peaksoft.gadgetarium2j7.model.entities.Order;
import com.peaksoft.gadgetarium2j7.model.entities.PayCard;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.model.enums.DeliveryOptions;
import com.peaksoft.gadgetarium2j7.model.enums.PaymentMethod;
import com.peaksoft.gadgetarium2j7.repository.OrderRepository;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final BasketMapper basketMapper;
    private final UserRepository userRepository;


    @Transactional
    public Order createOrderAndSave(String deliveryOption, Principal principal) {
        BasketResponse basketResponse = basketService.getBasket(principal);
        Basket basket = basketMapper.mapToBasket(basketResponse);

        Order order = new Order();
        order.setBasket(basket);
        order.setDeliveryOptions(DeliveryOptions.valueOf(deliveryOption));
        order.setOrderDate(LocalDate.now());
        return orderRepository.save(order);
    }

    public Order addUserInfoToOrder(Order order, Principal principal) {
        Long userId = Long.valueOf(principal.getName());

        Optional<User> userInfoOptional = userRepository.findUserInfoById(userId);
        User userInfo = userInfoOptional.orElseThrow(() -> new EntityNotFoundException("User not found"));

        order.setName(userInfo.getName());
        order.setLastName(userInfo.getLastName());
        order.setEmail(userInfo.getEmail());
        order.setPhoneNumber(userInfo.getTelNumber());

        if (userInfo.getAddress().equals(DeliveryOptions.DELIVERY_BY_COURIER.name())) {
            order.setUserAddress(userInfo.getAddress());
        } else if (userInfo.getAddress().equals(DeliveryOptions.PICKUP.name())) {
            order.setUserAddress("Самовывоз");
        }
        return orderRepository.save(order);
    }

    public Order addPaymentMethodToOrder(Principal principal, PaymentMethod paymentMethod, PayCard payCard) {
        String userEmail = principal.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Order order = new Order();
        order.setUsers((List<User>) user);
        order.setPaymentMethod(paymentMethod);

        if (paymentMethod == PaymentMethod.ONLINE_PAYMENT) {
            order.setPayCard(payCard);
        }

        return orderRepository.save(order);
    }
    public String getOrderDeliveryAddress(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Заказ не найден"));
        return order.getAddress();
    }

    public PaymentMethod getOrderPaymentStatus(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Заказ не найден"));

        return order.getPaymentMethod();
    }

    public int generateSevenDigitRandomNumber() {
        Random random = new Random();
        return 1000000 + random.nextInt(9000000);
    }
}
