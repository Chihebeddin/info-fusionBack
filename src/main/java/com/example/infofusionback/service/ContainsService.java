package com.example.infofusionback.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.infofusionback.entity.Contains;
import com.example.infofusionback.entity.OrderEntity;
import com.example.infofusionback.entity.Product;

@Service
public interface ContainsService {
	
	Contains newLine(Product p, OrderEntity o, int qte);
	
	List<Contains> userOrdersDetails(long id);

}
