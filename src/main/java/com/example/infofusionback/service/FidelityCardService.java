package com.example.infofusionback.service;

import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.FidelityCard;
import com.example.infofusionback.exception.IncorrectAmountException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FidelityCardService {
	
	FidelityCard getById(Long id);
	
	FidelityCard findByUser(long id);
	
	List<FidelityCard> getAllFidelityCards();
	
	FidelityCard saveFidelityCard(FidelityCard fidelityCard, Client c);
	
	FidelityCard addPoints(long clientId, double points, double solde);
	
	FidelityCard refundCard(long clientId, double solde);
	
	void deleteFidelityCard(Long id);
}
