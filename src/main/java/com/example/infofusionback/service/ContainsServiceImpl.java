package com.example.infofusionback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.infofusionback.entity.Contains;
import com.example.infofusionback.repository.ContainsRepository;

@Service
public class ContainsServiceImpl implements ContainsService {
	
	@Autowired
	ContainsRepository repo;

	@Override
	public Contains newLine(Contains c) {
		return repo.save(c);
	}

	@Override
	public List<Contains> userOrdersDetails(long id) {
		return repo.findByIdOrderEntity(id);
	}


}
