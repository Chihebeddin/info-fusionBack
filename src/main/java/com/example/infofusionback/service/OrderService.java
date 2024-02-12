package com.example.infofusionback.service;

import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.OrderEntity;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface OrderService {

	OrderEntity getOrderById(long id);
	
	List<OrderEntity> getUserOrders(long id);

	List<OrderEntity> allOrders();

	OrderEntity addOrder(OrderEntity o, Client c);
	
	OrderEntity updateOrder(Long id, OrderEntity o);
	
	void deleteById(long id);
}
