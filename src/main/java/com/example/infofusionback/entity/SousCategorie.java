package com.example.infofusionback.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity

@PrimaryKeyJoinColumn ( name ="id_SousCategorie")
public class SousCategorie {

    @Column
    private String libelleSousCat;


    public SousCategorie(String libelleSousCat) {
        this.libelleSousCat = libelleSousCat;
    }

    public String getLibelleSousCat() {
        return libelleSousCat;
    }

    public void setLibelleSousCat(String libelleSousCat) {
        this.libelleSousCat = libelleSousCat;
    }

    public SousCategorie() {
    }

    @OneToMany(mappedBy = "sousCategorie" , cascade = CascadeType.ALL)
    private List<Produit> produits ;


    @ManyToOne
    @JoinColumn (name = "id_categorie")
    private Categorie categorie;
}
