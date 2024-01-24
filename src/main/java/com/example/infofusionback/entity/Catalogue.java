package com.example.infofusionback.entity;

import jakarta.persistence.*;

@Entity

@PrimaryKeyJoinColumn( name ="id_Catalogue")
public class Catalogue {

    @Column
    private String prixVente ;

    @Column
    private Integer quantite;

    @Column
    private Boolean disponibilite;

    public String getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(String prixVente) {
        this.prixVente = prixVente;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Catalogue(String prixVente, Integer quantite, Boolean disponibilite) {
        this.prixVente = prixVente;
        this.quantite = quantite;
        this.disponibilite = disponibilite;
    }

    public Catalogue() {
    }

    @ManyToOne
    @JoinColumn(name="id_panier")
    private Panier panier;

    @OneToOne
    @JoinColumn(name="id_produit")
    private Produit produit ;
}
