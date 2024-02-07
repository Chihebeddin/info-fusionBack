package com.example.infofusionback.controller;


import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.Contains;
import com.example.infofusionback.entity.OrderEntity;
import com.example.infofusionback.entity.Product;
import com.example.infofusionback.service.ClientService;
import com.example.infofusionback.service.ContainsService;
import com.example.infofusionback.service.FidelityCardService;
import com.example.infofusionback.service.OrderService;
import com.example.infofusionback.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	protected OrderService orderService;

	@Autowired
	protected ClientService clientService;
	
	@Autowired
	protected ContainsService ctntService;
	
	@Autowired
	protected ProductService ps;
	
	@Autowired
	protected FidelityCardService fcs;

	@GetMapping("/{id}")
	public OrderEntity orderById(@PathVariable long id) {
		return orderService.getOrderById(id);
	}


	@GetMapping()
	public List<OrderEntity> products() {
		return orderService.allOrders();
	}

	
	@PostMapping("/create")
	public OrderEntity createOrder(@RequestBody List<Product> products, @RequestParam(value="client")long clientId) {
		OrderEntity o = new OrderEntity(LocalDateTime.now(), "Carte bancaire", "En cours");
		Client c = this.clientService.getClientById(clientId);
		
		orderService.addOrder(o, c);
		
		double total = 0;
		
		for (Product p: products) {
			Product newP = ps.getProductById(p.getId());
			Contains content = new Contains(newP, o);
			content.setQuantity(p.getQuantity());
			ctntService.newLine(content);
			total+= p.getPrice() * p.getQuantity();
			
			o.addContent(content);
		}
		
		fcs.addPoints(clientId, total, -total);
		
		return orderService.updateOrder(o.getId(), o);
	}


}
