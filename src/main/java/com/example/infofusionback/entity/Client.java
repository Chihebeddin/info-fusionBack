package com.example.infofusionback.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

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
	
	public Client() {

	}

	public Client(String firstName, String lastName, int phone, Date birthdate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.birthdate = birthdate;
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
	
	
	
	
}
