package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.dto.OrderRequest;
import com.peaksoft.gadgetarium2j7.model.dto.OrderResponse;
import com.peaksoft.gadgetarium2j7.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
