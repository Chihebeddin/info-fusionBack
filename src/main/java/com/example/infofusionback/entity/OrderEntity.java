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
	protected Long id;

	@Column
	protected LocalDateTime validationDate;

	@Column
	protected String paymentOption;

	@Column
	protected String status;

	public OrderEntity() {}


	public OrderEntity(LocalDateTime d, String option, String status) {
		this.validationDate = d;
		this.paymentOption = option;
		this.status = status;
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

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_user")
	@JsonIgnore
	protected Client client;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@OneToMany(mappedBy="id.order", fetch=FetchType.EAGER)
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
