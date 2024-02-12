package com.example.infofusionback.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.infofusionback.entity.Contains;

@Service
public interface ContainsService {
	
	Contains newLine(Contains c);
	
	List<Contains> userOrdersDetails(long id);

}
