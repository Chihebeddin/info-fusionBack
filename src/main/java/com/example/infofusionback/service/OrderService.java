package com.example.infofusionback.service;

import com.example.infofusionback.entity.OrderEntity;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface OrderService {

	OrderEntity getOrderById(long id);

	List<OrderEntity> allOrders();
}
