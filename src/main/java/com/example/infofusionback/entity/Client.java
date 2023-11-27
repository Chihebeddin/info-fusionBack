package com.example.infofusionback.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn ( name =" id ")
public class Client extends User {
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private int phone ;
	
	@Column
	private Date birthdate;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private User user;

	public Client() {

	}

	public Client(Long id, String email, String password, LocalDateTime d, String role, Client client, Shop shop, String firstName, String lastName, int phone, Date birthdate, User user) {
		super(id, email, password, d, role, client, shop);
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.birthdate = birthdate;
		this.user = user;
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
