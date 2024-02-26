package com.example.infofusionback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.infofusionback.entity.Contains;
import com.example.infofusionback.entity.OrderEntity;
import com.example.infofusionback.entity.Product;
import com.example.infofusionback.repository.ContainsRepository;

@Service
public class ContainsServiceImpl implements ContainsService {
	
	@Autowired
	ContainsRepository repo;

	@Override
	public List<Contains> userOrdersDetails(long id) {
		return repo.findByIdOrderEntity(id);
	}

	@Override
	public Contains newLine(Product p, OrderEntity o, int qte) {
		Contains content = new Contains(p, o, qte);
		return repo.save(content);
	}


}
