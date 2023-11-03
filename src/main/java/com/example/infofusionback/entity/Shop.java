package com.example.infofusionback.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

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
	
	
	
	
	public Shop() {

	}

	public Shop(String name, String openingTime, int phone, String closingTime) {
		super();
		this.name = name;
		this.openingTime = openingTime;
		this.phone = phone;
		this.closingTime = closingTime;
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
	
	
	
	
	
	
}
