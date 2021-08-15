package com.Java6.rest.controller;

import com.Java6.entity.Order;
import com.Java6.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/orders/")
public class OrderRestController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "test")
    public void test() {
        orderService.print();
    }

    @PostMapping()
    public Order create(@RequestBody JsonNode orderData) {
        orderService.print();
        return orderService.create(orderData);
    }
}
