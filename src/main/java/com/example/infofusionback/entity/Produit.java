package com.example.infofusionback.entity;


import jakarta.persistence.*;

import java.util.List;

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

     @OneToMany(mappedBy = "produit" , cascade = CascadeType.ALL)
     private List<Catalogue> catalogues;

    @OneToOne
    @JoinColumn(name = "id_SousCategorie")
    private SousCategorie sousCategorie;

}
