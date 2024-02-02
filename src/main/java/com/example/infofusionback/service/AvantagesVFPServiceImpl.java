package com.example.infofusionback.service;

import com.example.infofusionback.entity.AvantagesVFP;
import com.example.infofusionback.repository.AvantagesVFPRepository;
import com.example.infofusionback.repository.ClientRepository;
import com.example.infofusionback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.infofusionback.entity.Client;


import java.util.List;
import java.util.Optional;

@Service
public class AvantagesVFPServiceImpl implements AvantagesVFPService{
    @Autowired
    private AvantagesVFPRepository avantagesVFPRepository;

    @Autowired
    private ClientRepository clientRepository; // Assuming you have a UserRepository

    @Override
    public List<AvantagesVFP> getAllAvantages() {
        return (List<AvantagesVFP>) avantagesVFPRepository.findAll();
    }

    @Override
    public AvantagesVFP getAvantageById(Long id) {
        Optional<AvantagesVFP> avantageOptional = avantagesVFPRepository.findById(id);
        return avantageOptional.orElse(null);
    }

    @Override
    public AvantagesVFP saveAvantage(AvantagesVFP avantage) {
        return avantagesVFPRepository.save(avantage);
    }

    @Override
    public void deleteAvantage(Long id) {
        avantagesVFPRepository.deleteById(id);
    }

    @Override
    public void addAvantageToClient(Long avantageId, Long clientId) {
        AvantagesVFP avantage = getAvantageById(avantageId);
        Client client = clientRepository.findById(clientId).orElse(null);

        if (avantage != null && client != null) {
            client.getAvantages().add(avantage);
            clientRepository.save(client);
        }
    }

    @Override
    public void removeAvantageFromClient(Long avantageId, Long clientId) {
        AvantagesVFP avantage = getAvantageById(avantageId);
        Client client = clientRepository.findById(clientId).orElse(null);

        if (avantage != null && client != null) {
            client.getAvantages().remove(avantage);
            clientRepository.save(client);
        }
    }
}
