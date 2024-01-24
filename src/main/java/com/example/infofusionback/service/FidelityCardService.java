package com.example.infofusionback.service;

import com.example.infofusionback.entity.FidelityCard;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FidelityCardService {
    FidelityCard getById(Long id);
    List<FidelityCard> getAllFidelityCards();
    FidelityCard saveFidelityCard(FidelityCard fidelityCard);
    void deleteFidelityCard(Long id);
}
