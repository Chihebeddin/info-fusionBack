package com.example.infofusionback.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn ( name ="id_user")
public class Client extends User {

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String phone ;

	@Column
	private Date birthdate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fidelityCard_id", referencedColumnName = "id")
	private FidelityCard fidelityCard;

	@OneToMany(mappedBy="client", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<OrderEntity> orders = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
			name = "client_avantage",
			joinColumns = @JoinColumn(name = "id_user"),
			inverseJoinColumns = @JoinColumn(name = "id_avantage")
	)
	private Set<AvantagesVFP> avantages = new HashSet<>();
	
	public Client() {

	}

	public Client(String email, String password, LocalDateTime d, String role) {
		super(email, password, d, role);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Set<OrderEntity> getOrders() {
		return this.orders;
	}

	public void addOrder(OrderEntity o) { this.orders.add(o); }

	public void setOrders(Set<OrderEntity> orders) {
		this.orders = orders;
	}

	public Set<AvantagesVFP> getAvantages() {
		return avantages;
	}
	
	public void addAvantage(AvantagesVFP a) { this.avantages.add(a); }
	
	public void removeAvantage(AvantagesVFP a) { this.removeAvantage(a); }

	public void setAvantages(Set<AvantagesVFP> avantages) {
		this.avantages = avantages;
	}
}
