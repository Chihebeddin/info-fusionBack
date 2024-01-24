package com.example.infofusionback.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn( name ="id_Produit")

public class Produit {

    @Column
    private String nomProduit ;

    public Produit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public Produit() {
    }
}
