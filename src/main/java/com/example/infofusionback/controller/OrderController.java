package com.example.infofusionback.controller;


import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.Contains;
import com.example.infofusionback.entity.OrderEntity;
import com.example.infofusionback.entity.Product;
import com.example.infofusionback.service.ClientService;
import com.example.infofusionback.service.ContainsService;
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

	@GetMapping("/{id}")
	public OrderEntity orderById(@PathVariable long id) {
		return orderService.getOrderById(id);
	}

	@GetMapping("/filtered")
	public List<OrderEntity> ordersByUser(@RequestParam(value="client") long id) {
		return orderService.getUserOrders(id);
	}
	
	@GetMapping()
	public List<OrderEntity> ordersByShop(@RequestParam(value="shop") long id) {
		return orderService.shopOrders(id);
	}

	/*@GetMapping()
	public List<OrderEntity> orders() {
		return orderService.allOrders();
	}*/

	
	@PostMapping("/create")
	public OrderEntity createOrder(@RequestBody List<Product> products, @RequestParam(value="client")long clientId) {
		OrderEntity o = new OrderEntity(LocalDateTime.now(), "Carte bancaire", "En cours");
		Client c = this.clientService.getClientById(clientId);
		
		orderService.addOrder(o, c);
		
		double total = 0;
		
		for (Product p: products) {
			Product newP = ps.getProductById(p.getId());
			Contains content = ctntService.newLine(newP, o, p.getQuantity());
			
			total+= p.getPrice() * p.getQuantity();
			
			o.addContent(content);
		}
		o.setTotal(total);
		
		return orderService.updateOrder(o.getId(), o);
	}
	
	@PutMapping("/{id}/accept")
	public OrderEntity acceptOrder(@PathVariable long id) {
		return orderService.acceptOrder(id);
	}
	
	@PutMapping("/{id}/end")
	public OrderEntity endOrder(@PathVariable long id) {
		return orderService.endOrder(id);
	}
	
	
	@PutMapping("/{id}")
	public OrderEntity updateOrderStatus(@PathVariable long id, @RequestParam(value="status")String status) {
		return orderService.changeStatus(id, status);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		orderService.deleteById(id);
	}

}
