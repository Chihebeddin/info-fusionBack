package com.example.infofusionback.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn ( name ="id_user")
public class Shop extends User {

	@Column
	private String name;

	@Column
	private String location;

	@Column
	private String phone ;

	@Column
	private String openingTime;

	@Column
	private String closingTime;


	public Shop() {

	}
	
	public Shop(String email, String password, LocalDateTime d, String role) {
			super(email, password, d, role);
		}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/
}
