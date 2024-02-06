package com.example.infofusionback.service;

import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.FidelityCard;
import com.example.infofusionback.repository.FidelityCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FidelityCardServiceImpl implements FidelityCardService {

	@Autowired
	private FidelityCardRepository fidelityCardRepository;

	@Override
	public FidelityCard getById(Long id) {
		return fidelityCardRepository.findById(id).orElse(null);
	}

	@Override
	public List<FidelityCard> getAllFidelityCards() {
		return (List<FidelityCard>) fidelityCardRepository.findAll();
	}

	@Override
	public FidelityCard saveFidelityCard(FidelityCard fidelityCard, Client c) {
		c.setFidelityCard(fidelityCard);
		fidelityCard.setClient(c);

		return fidelityCardRepository.save(fidelityCard);
	}

	@Override
	public void deleteFidelityCard(Long id) {
		fidelityCardRepository.deleteById(id);
	}

	@Override
	public FidelityCard findByUser(long id) {
		return this.fidelityCardRepository.findByIdUser(id);
	}

	@Override
	public FidelityCard addPoints(long id, double p, double s) {
		FidelityCard fc = this.findByUser(id);

		fc.addPoints(p);
		fc.updateSolde(s);
		fc.setDatePoints(LocalDateTime.now());

		return fidelityCardRepository.save(fc);
	}

	@Override
	public FidelityCard refundCard(long clientId, double solde) {
		FidelityCard fc = this.findByUser(clientId);

		fc.updateSolde(solde);
		
		return fidelityCardRepository.save(fc);
	}
}