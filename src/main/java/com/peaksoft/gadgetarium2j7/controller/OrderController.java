package com.peaksoft.gadgetarium2j7.controller;


import com.peaksoft.gadgetarium2j7.exception.EntityNotFoundException;
import com.peaksoft.gadgetarium2j7.exception.UnauthorizedException;
import com.peaksoft.gadgetarium2j7.model.entities.Order;
import com.peaksoft.gadgetarium2j7.model.entities.PayCard;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.model.enums.PaymentMethod;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import com.peaksoft.gadgetarium2j7.service.BasketService;
import com.peaksoft.gadgetarium2j7.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;
    private final BasketService basketService;



    @PostMapping("/order")
    public Order createOrder(@RequestBody String deliveryOption, Principal principal) {
        if (principal == null) {
            throw new UnauthorizedException("Пользователь не аутентифицирован");
        }
        return orderService.createOrderAndSave(deliveryOption, principal);
    }
    @PostMapping("/order_info")
    public ResponseEntity<Order> createOrder(@RequestBody Order order, Principal principal) {
        try {
            Order createdOrder = orderService.addUserInfoToOrder(order, principal);
            return ResponseEntity.ok(createdOrder);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/orders/addPaymentMethod")
    public ResponseEntity<Order> addPaymentMethodToOrder(Principal principal,
                                                         @RequestParam PaymentMethod paymentMethod,
                                                         @RequestBody PayCard payCard) {
        try {
            User user = userRepository.findByEmail(principal.getName())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            Order order = orderService.addPaymentMethodToOrder((Principal) user, paymentMethod, payCard);
            return ResponseEntity.ok(order);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/orders/summary")
    public ResponseEntity<List<Object[]>> getOrderSummary(Principal principal) {
        try {
            List<Object[]> summary = orderService.getTotalAmountAndDeliveryInfo(principal);
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/orders/generateRandomNumber")
    public String generateRandomNumberWithPrefix() {
        int randomNumber = orderService.generateSevenDigitRandomNumber();
        return "НОМЕР ЗАЯВКИ " + randomNumber;
    }
    @GetMapping("/orders/count")
    public int countOrderByUserAndDateRange(@RequestParam String email,
                                            @RequestParam LocalDateTime startDate,
                                            @RequestParam LocalDateTime endDate) {
        return orderService.countOrderByUserAndDateRange(email, startDate, endDate);
    }
    @PostMapping("/clearBasket")
    public void clearBasket(Principal principal) {
        basketService.clearBasket(principal);
    }
}