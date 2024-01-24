package com.example.infofusionback.entity;


import jakarta.persistence.*;

import java.util.List;

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

    @OneToMany(mappedBy = "categorie" , cascade = CascadeType.ALL)
    private List<SousCategorie> sousCategories;
}
