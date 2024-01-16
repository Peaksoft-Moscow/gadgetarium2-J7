package com.peaksoft.gadgetarium2j7.model.dto;

import com.peaksoft.gadgetarium2j7.model.entities.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;


@Getter
@Setter
public class OrderDto{
    private String address;
    private int sum;
    private String userAddress;
    private Delivery delivery;
    private PayCard payCard;
    private List<Product> products;
    private List<User> users;
    private Basket basket;
}