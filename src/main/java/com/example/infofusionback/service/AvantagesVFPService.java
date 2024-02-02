package com.example.infofusionback.service;

import com.example.infofusionback.entity.AvantagesVFP;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AvantagesVFPService {
    List<AvantagesVFP> getAllAvantages();

    AvantagesVFP getAvantageById(Long id);

    AvantagesVFP saveAvantage(AvantagesVFP avantage);

    void deleteAvantage(Long id);

    void addAvantageToClient(Long avantageId, Long clientId);

    void removeAvantageFromClient(Long avantageId, Long clientId);
}
