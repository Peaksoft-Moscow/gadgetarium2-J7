package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.dto.*;
import com.peaksoft.gadgetarium2j7.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/making-an-order")
@RequiredArgsConstructor
public class MakingAnOrderController {
        private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> add(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.create(orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")Long id){
        orderService.delete(id);
        return "Delete order with id:" + id +" successfully delete";
    }

    @GetMapping()
    public List<OrderResponse> getAllOrders(){
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

}
