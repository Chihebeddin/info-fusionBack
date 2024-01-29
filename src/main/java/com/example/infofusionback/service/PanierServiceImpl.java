package com.example.infofusionback.service;

import com.example.infofusionback.entity.Panier;
import com.example.infofusionback.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PanierServiceImpl  implements PanierService {



    @Autowired
    protected  PanierRepository panierRepository ;
    @Override
    public Panier getPanierById(long id) {
        return panierRepository.findById(id);
    }

    @Override
    public List<Panier> allPaniers() {
        return panierRepository.findAll();
    }
}
