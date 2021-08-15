package com.Java6.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.Java6.JPA.OrderDetailsRepository;
import com.Java6.JPA.OrderRepository;
import com.Java6.Util.ObjectMapperUtils;
import com.Java6.dto.OrderDto;
import com.Java6.entity.Order;
import com.Java6.entity.OrderDetail;
import com.Java6.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private ObjectMapperUtils objectMapperUtils;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Order create(JsonNode orderData) {
        Order order = objectMapper.convertValue(orderData, Order.class);
        orderRepository.save(order);

        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
        };
        List<OrderDetail> orderDetails = objectMapper.convertValue(orderData.get("orderDetails"), type).stream()
                .peek(d -> d.setOrder(order)).collect(Collectors.toList());
        orderDetailsRepository.saveAll(orderDetails);
        return order;
    }

    @Override
    public void print() {
        System.out.println("2");
    }

    @Override
    public Order findById(long id) {
        // Order order = orderRepository.findById(id).get();
        // OrderDto result = objectMapperUtils.convertEntityAndDto(order, OrderDto.class);
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> findByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

}
