package com.example.infofusionback.service;

import com.example.infofusionback.entity.FidelityCard;
import com.example.infofusionback.repository.FidelityCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public FidelityCard saveFidelityCard(FidelityCard fidelityCard) {
        return fidelityCardRepository.save(fidelityCard);
    }

    @Override
    public void deleteFidelityCard(Long id) {
        fidelityCardRepository.deleteById(id);
    }
}