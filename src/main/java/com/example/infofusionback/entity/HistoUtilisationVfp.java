package com.example.infofusionback.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class HistoUtilisationVfp {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_HistoStatut")
    private Long id;

    @Column(name = "id_user")
    private Long iduser;

    @Column(name = "id_avantage")
    private Long idAvantage;

    @Column
    private Date DateHisto;

    public HistoUtilisationVfp(Long id, Long iduser, Long idAvantage, Date dateHisto) {
        this.id = id;
        this.iduser = iduser;
        this.idAvantage = idAvantage;
        DateHisto = dateHisto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public Long getIdAvantage() {
        return idAvantage;
    }

    public void setIdAvantage(Long idAvantage) {
        this.idAvantage = idAvantage;
    }

    public Date getDateHisto() {
        return DateHisto;
    }

    public void setDateHisto(Date dateHisto) {
        DateHisto = dateHisto;
    }
}
