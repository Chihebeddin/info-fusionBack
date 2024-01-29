package com.example.infofusionback.service;

import com.example.infofusionback.entity.Panier;

import java.util.List;

public interface PanierService {
    Panier getPanierById(long id);

    List<Panier> allPaniers();
}
