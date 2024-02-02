package com.example.infofusionback.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class AvantagesVFP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avantage")
    private Long id;

    @Column(name = "libelle_avantage")
    private String libelleAvantage;

    @ManyToMany(mappedBy = "avantages")
    private Set<Client> clients = new HashSet<>();

    public AvantagesVFP() {

    }
    public AvantagesVFP(Long id, String libelleAvantage, Set<Client> clients) {
        this.id = id;
        this.libelleAvantage = libelleAvantage;
        this.clients = clients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleAvantage() {
        return libelleAvantage;
    }

    public void setLibelleAvantage(String libelleAvantage) {
        this.libelleAvantage = libelleAvantage;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}

