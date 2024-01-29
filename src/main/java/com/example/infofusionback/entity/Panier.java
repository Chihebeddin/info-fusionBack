package com.example.infofusionback.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
    @PrimaryKeyJoinColumn( name ="id_panier")
    public class Panier  {

    @EmbeddedId
    PanierId id;

    public PanierId getId() {
        return id;
    }

    public void setId(PanierId id) {
        this.id = id;
    }

    public Client getClient(){
        return getId().getClient();
    }

    public Product getProduct(){
        return getId().getProduct();
    }
    public void setClient(Client client){
        getId().setClient(client);
    }
    public void setProduct (Product product){
        getId().setProduct(product);
    }
    @Column
        private int quantite;

        @Column
        private Date dateAchat;

        @Column
        private String ModePaiement;


    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public String getModePaiement() {
        return ModePaiement;
    }

    public void setModePaiement(String modePaiement) {
        ModePaiement = modePaiement;
    }

    public List<Catalogue> getCatalogues() {
        return catalogues;
    }

    public void setCatalogues(List<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    private List<Catalogue> catalogues;
}
