package com.example.infofusionback.controller;


import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.Panier;
import com.example.infofusionback.entity.Product;
import com.example.infofusionback.entity.Shop;
import com.example.infofusionback.service.ClientService;
import com.example.infofusionback.service.PanierService;
import com.example.infofusionback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paniers")
public class PanierController {

    @Autowired
    protected PanierService panierService;

    @Autowired
    protected ClientService clientService ;

    @GetMapping("/{id}")
    public Panier panierById(@PathVariable long id) {
        return panierService.getPanierById(id);
    }



    @GetMapping()
    public List<Panier> products() {
        return panierService.allPaniers();
    }

    @PostMapping("/create")
    public Panier createPanier(@RequestBody Panier panier, @RequestParam(value="client")long clientId) {
        Client client = clientService.getClientById(clientId);
        //productService.addProduct(product, shop);
        //on peut ajouter liste des produits

        return null;
    }



}
