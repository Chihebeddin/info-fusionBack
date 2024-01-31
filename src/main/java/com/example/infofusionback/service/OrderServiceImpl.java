package com.example.infofusionback.service;

import com.example.infofusionback.entity.OrderEntity;
import com.example.infofusionback.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl  implements OrderService {

	@Autowired
	protected  OrderRepository orderRepository ;

	@Override
	public OrderEntity getOrderById(long id) {
		return orderRepository.findById(id);
	}

	@Override
	public List<OrderEntity> allOrders() {
		return orderRepository.findAll();
	}
}
