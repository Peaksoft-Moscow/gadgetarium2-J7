package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.exception.EntityNotFoundException;
import com.peaksoft.gadgetarium2j7.exception.UnauthorizedException;
import com.peaksoft.gadgetarium2j7.model.entities.Order;
import com.peaksoft.gadgetarium2j7.model.entities.PayCard;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.model.enums.PaymentMethod;
import com.peaksoft.gadgetarium2j7.repository.OrderRepository;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import com.peaksoft.gadgetarium2j7.service.BasketService;
import com.peaksoft.gadgetarium2j7.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;
    private final BasketService basketService;
    private final OrderRepository orderRepository;


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

    @GetMapping("/total")
    public ResponseEntity<Double> getTotalBasketAmount(Principal principal) {
        double totalAmount = basketService.getTotalBasketAmount(principal);
        return ResponseEntity.ok(totalAmount);
    }

    @GetMapping("/{orderId}/deliveryAddress")
    public ResponseEntity<String> getOrderDeliveryAddress(@PathVariable Long orderId) {
        try {
            String deliveryAddress = orderService.getOrderDeliveryAddress(orderId);
            return ResponseEntity.ok(deliveryAddress);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{orderId}/paymentStatus")
    public ResponseEntity<PaymentMethod> getOrderPaymentStatus(@PathVariable Long orderId) {
        try {
            PaymentMethod paymentMethod = orderService.getOrderPaymentStatus(orderId);
            return ResponseEntity.ok(paymentMethod);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PaymentMethod.valueOf(e.getMessage()));
        }
    }

    @GetMapping("/generateRandomNumber")
    public ResponseEntity<String> generateRandomNumberWithPrefix() {
        int randomNumber = orderService.generateSevenDigitRandomNumber();
        String emailContent = "НОМЕР ЗАЯВКИ " + randomNumber;

        sendEmail("recipient@example.com", "Новый заказ", emailContent);

        return ResponseEntity.ok(emailContent);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        MailSender javaMailSender = null;
        javaMailSender.send(message);
    }

    @PostMapping("/clearBasket")
    public void clearBasket(Principal principal) {
        basketService.clearBasket(principal);
    }
}