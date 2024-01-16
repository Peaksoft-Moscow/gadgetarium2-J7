package com.peaksoft.gadgetarium2j7.mapper;

import com.peaksoft.gadgetarium2j7.model.dto.OrderRequest;
import com.peaksoft.gadgetarium2j7.model.dto.OrderResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order mapToEntity(OrderRequest request){
        Order order = new Order();
        order.setAddress(request.getAddress());
        order.setDelivery(request.getDelivery());
        order.setBasket(request.getBasket());
        order.setProducts(request.getProducts());
        order.setSum(request.getSum());
        order.setUsers(request.getUsers());
        order.setPayCard(request.getPayCard());
        order.setUserAddress(request.getUserAddress());
        return order;
    }

    public OrderResponse mapToResponse(Order order){
        return OrderResponse.builder()
                .address(order.getAddress())
                .id(order.getId())
                .sum(order.getSum())
                .users(order.getUsers())
                .payCard(order.getPayCard())
                .delivery(order.getDelivery())
                .basket(order.getBasket())
                .userAddress(order.getUserAddress())
                .products(order.getProducts())
                .build();
    }
}
