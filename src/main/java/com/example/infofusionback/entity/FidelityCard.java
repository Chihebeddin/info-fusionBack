package com.example.infofusionback.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class FidelityCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fidelityCard_id")
    private Long id;

    @Column
    private int nbPoints;

    @Column
    private double solde;

    @Column
    private LocalDateTime datePoints;

    @OneToOne(mappedBy = "fidelityCard")
    private Client client;

    public FidelityCard() {
    }

    public FidelityCard(Long id, int nbPoints, double solde, LocalDateTime datePoints, byte[] qrCodePath) {
        this.id = id;
        this.nbPoints = nbPoints;
        this.solde = solde;
        this.datePoints = datePoints;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public LocalDateTime getDatePoints() {
        return datePoints;
    }

    public void setDatePoints(LocalDateTime datePoints) {
        this.datePoints = datePoints;
    }

}
