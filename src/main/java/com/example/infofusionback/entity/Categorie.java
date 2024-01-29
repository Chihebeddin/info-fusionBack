package com.example.infofusionback.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn ( name ="id_Categorie")
public class Categorie {


    @Column
    private String libelleCategorie;

    public Categorie(String libelleCategorie) {
        this.libelleCategorie = libelleCategorie;
    }

    public String getLibelleCategorie() {
        return libelleCategorie;
    }

    public void setLibelleCategorie(String libelleCategorie) {
        this.libelleCategorie = libelleCategorie;
    }

    public Categorie() {
    }

    @OneToMany
    @JoinColumn(name = "id_product")
    private Set<Product> products = new HashSet<Product>();

    public Set<Product> getProducts() {
        return this.products;

    }

    public void addProduct(Product p){
        products.add(p);
    }

    public void removeProduct(Product p){
        products.remove(p);
    }


}
