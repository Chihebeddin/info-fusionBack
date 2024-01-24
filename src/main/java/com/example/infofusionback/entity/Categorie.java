package com.example.infofusionback.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

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
}
