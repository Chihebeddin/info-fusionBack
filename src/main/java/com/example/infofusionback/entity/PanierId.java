package com.example.infofusionback.entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PanierId  implements  java.io.Serializable{

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Client client;


    @ManyToOne
    @JoinColumn(name="id_product")
    private Product product;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
