package com.example.infofusionback.entity;

import jakarta.persistence.*;


@Entity(name = "shoptypes")
@Table(name = "shoptypes")
public class ShopType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_shoptype")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EShopType type;


    public ShopType(Integer id, EShopType type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EShopType getType() {
        return type;
    }

    public void setType(EShopType type) {
        this.type = type;
    }
}
