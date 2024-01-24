package com.example.infofusionback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.util.Date;


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


}
