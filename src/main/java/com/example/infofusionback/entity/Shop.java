package com.example.infofusionback.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn ( name =" id ")
public class Shop extends User {
	
	@Column
	private String name;
	
	@Column
	private String location;
	
	@Column
	private int phone ;
	
	@Column
	private String openingTime;
	
	@Column
	private String closingTime;

	/*@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "shop_id", referencedColumnName = "id")
	private User user;*/
	
	
	public Shop() {

	}

	public Shop(Long id, String email, String password, LocalDateTime d, String role, Client client, Shop shop, String name, String location, int phone, String openingTime, String closingTime/*, User user*/) {
		super(id, email, password, d, role/*, client, shop*/);
		this.name = name;
		this.location = location;
		this.phone = phone;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		//this.user = user;
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
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
