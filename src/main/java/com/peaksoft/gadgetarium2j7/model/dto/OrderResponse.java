package com.peaksoft.gadgetarium2j7.model.dto;

import com.peaksoft.gadgetarium2j7.model.entities.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponse {
    private Long id;
    private String address;
    private int sum;
    private String userAddress;
    private Delivery delivery;
    private PayCard payCard;
    private List<Product> products;
    private List<User> users;
    private Basket basket;
    private LocalDate localDate;
}