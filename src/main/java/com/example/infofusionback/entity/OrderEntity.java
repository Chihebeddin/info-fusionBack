package com.example.infofusionback.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_order_entity")
	protected Long id;

	@Column
	protected LocalDateTime validationDate;
	
	@Column
	protected LocalDateTime collectDate;

	@Column
	protected String paymentOption;

	@Column
	protected String status;
	
	@Column
	protected double total;

	public OrderEntity() {}


	public OrderEntity(LocalDateTime d, String option, String status) {
		this.validationDate = d;
		this.setPaymentOption(option);
		this.status = status;
		this.total = 0;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(LocalDateTime validationDate) {
		this.validationDate = validationDate;
	}
	
	
	public LocalDateTime getCollectDate() {
		return collectDate;
	}


	public void setCollectDate(LocalDateTime collectDate) {
		this.collectDate = collectDate;
	}


	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		if( paymentOption.equals("Cash") ) {
			this.paymentOption = "Espèces";
		}
		else if ( paymentOption.equals("CreditCard") ) {
			this.paymentOption = "Carte bancaire";
		}
		else if ( paymentOption.equals("FidelityCard") ) {
			this.paymentOption = "Carte de fidélité/paiement";
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_user")
	protected Client client;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@OneToMany(mappedBy="id.orderEntity", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Contains> content = new HashSet<Contains>();
	public Set<Contains> getContent() {
		return this.content;
	}

	public void setContent(Set<Contains> c) {
		this.content = c;
	}
	
	public void addContent(Contains c) { this.content.add(c); }
	

}
