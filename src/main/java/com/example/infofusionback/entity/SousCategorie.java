package com.example.infofusionback.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

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
}
