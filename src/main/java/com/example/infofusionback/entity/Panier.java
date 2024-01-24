package com.example.infofusionback.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
    @PrimaryKeyJoinColumn( name ="id_panier")
    public class Panier extends User {

        @Column
        private String libelle;

        @Column
        private Date dateCreation ;

        @Column
        private Boolean valide;

    public Panier(String libelle, Date dateCreation, Boolean valide) {
        this.libelle = libelle;
        this.dateCreation = dateCreation;
        this.valide = valide;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public Panier() {
    }

    @ManyToOne
    @JoinColumn(name="id_user" )
    private Client client;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    private List<Catalogue> catalogues;
}
