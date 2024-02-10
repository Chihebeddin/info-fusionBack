package com.example.infofusionback.service;

import com.example.infofusionback.entity.Client;
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

	@Override
	public OrderEntity addOrder(OrderEntity o, Client c) {
		c.addOrder(o);
		o.setClient(c);
		return orderRepository.save(o);
	}

	@Override
	public OrderEntity updateOrder(Long id, OrderEntity o) {
		OrderEntity ord = this.getOrderById(id);
		ord.setContent(o.getContent());
		return orderRepository.save(ord);
	}

	@Override
	public List<OrderEntity> getUserOrders(long id) {
		return orderRepository.findByIdUser(id);
	}

	@Override
	public void deleteById(long id) {
		orderRepository.deleteById(id);
		
	}
}
