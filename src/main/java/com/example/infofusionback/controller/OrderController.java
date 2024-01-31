package com.example.infofusionback.controller;


import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.OrderEntity;
import com.example.infofusionback.service.ClientService;
import com.example.infofusionback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	protected OrderService orderService;

	@Autowired
	protected ClientService clientService ;

	@GetMapping("/{id}")
	public OrderEntity orderById(@PathVariable long id) {
		return orderService.getOrderById(id);
	}


	@GetMapping()
	public List<OrderEntity> products() {
		return orderService.allOrders();
	}

	/*
	@PostMapping("/create")
	public Order createOrder(@RequestBody Order order, @RequestParam(value="client")long clientId) {
		
	}*/


}
