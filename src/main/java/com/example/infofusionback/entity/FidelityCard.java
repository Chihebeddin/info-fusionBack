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
	private double nbPoints;

	@Column
	private double solde;

	@Column
	private LocalDateTime datePoints;

    @OneToOne(mappedBy = "fidelityCard")
    private Client client;

	public FidelityCard() {
		this.nbPoints = 0;
		this.solde = 0;
	}

	public FidelityCard(Long id, double nbPoints, double solde, LocalDateTime datePoints, byte[] qrCodePath) {
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

	public double getNbPoints() {
		return nbPoints;
	}
	
	public double addPoints(double p) {
		this.nbPoints+= p;
		return this.nbPoints;
	}

	public void setNbPoints(double nbPoints) {
		this.nbPoints = nbPoints;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	public double updateSolde(double amount) {
		this.solde+= amount;
		return this.solde;
	}

	public LocalDateTime getDatePoints() {
		return datePoints;
	}

	public void setDatePoints(LocalDateTime datePoints) {
		this.datePoints = datePoints;
	}

	@OneToOne
	@JoinColumn(name="id_user")
	private Client client;


	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	

}
