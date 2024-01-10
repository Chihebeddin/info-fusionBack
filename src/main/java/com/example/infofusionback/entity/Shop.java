package com.example.infofusionback.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "shops_types",
			joinColumns = @JoinColumn(name = "id_user"),
			inverseJoinColumns = @JoinColumn(name = "id_shoptype"))
	private Set<ShopType> shopType = new HashSet<>();



	public Shop() {

	}
	
	public Shop(String email, String password, LocalDateTime d, String role) {
		super(email, password, d, role);
		}


	public Shop(String email, String password, LocalDateTime d, String role, String name, String location, String phone, String openingTime, String closingTime, Set<ShopType> shopType) {
		super(email, password, d, role);
		this.name = name;
		this.location = location;
		this.phone = phone;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.shopType = shopType;
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

	public Set<ShopType> getShopType() {
		return shopType;
	}

	public void setShopType(Set<ShopType> shopType) {
		this.shopType = shopType;
	}
}
