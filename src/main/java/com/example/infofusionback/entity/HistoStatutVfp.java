package com.example.infofusionback.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class HistoStatutVfp {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_HistoStatut")
    private Long id;

    @Column
    private Date DateHisto;



    @Column(name = "id_user")
    private Long iduser;

    @Column
    private String EstVfp;

    public HistoStatutVfp() {
    }

    public Long getId() {
        return id;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateHisto() {
        return DateHisto;
    }

    public void setDateHisto(Date dateHisto) {
        DateHisto = dateHisto;
    }

    public String getEstVfp() {
        return EstVfp;
    }

    public void setEstVfp(String estVfp) {
        EstVfp = estVfp;
    }

    public HistoStatutVfp(Long id, Date dateHisto, String estVfp) {
        this.id = id;
        DateHisto = dateHisto;
        EstVfp = estVfp;
    }
}
