package com.example.infofusionback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.infofusionback.entity.Contains;
import com.example.infofusionback.entity.OrderEntity;
import com.example.infofusionback.service.ContainsService;
import com.example.infofusionback.service.OrderService;

@RestController
@RequestMapping("/contains")
public class ContainsController {
	
	@Autowired
	protected ContainsService cs;
	
	@Autowired
	protected OrderService os;
	
	@GetMapping("/{id}")
	public List<Contains> getOrderContent(@PathVariable long id) {
		return cs.userOrdersDetails(id);
	}

}
