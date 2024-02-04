package com.peaksoft.gadgetarium2j7.service;

import com.peaksoft.gadgetarium2j7.exception.EntityNotFoundException;
import com.peaksoft.gadgetarium2j7.mapper.OrderMapper;
import com.peaksoft.gadgetarium2j7.model.dto.OrderRequest;
import com.peaksoft.gadgetarium2j7.model.dto.OrderResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Order;
import com.peaksoft.gadgetarium2j7.repository.OrderRepository;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderResponse create(OrderRequest request){
            Order order = orderMapper.mapToEntity(request);
            orderRepository.save(order);
            return orderMapper.mapToResponse(order);
        }

    public List<OrderResponse> getAll() {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orderRepository.findAll()){
            orderResponses.add(orderMapper.mapToResponse(order));
        }
    return orderResponses;
    }

    public void delete(Long orderId){
        orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with <id: "+orderId+"> not found"));
        orderRepository.deleteById(orderId);
    }

    public OrderResponse getOrderById(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with <id: "+orderId+"> not found"));
        return orderMapper.mapToResponse(order);
    }

}
