package com.example.infofusionback.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Lob // Utilisé pour stocker de gros objets, comme des images
	@Column(length = 1048576)
	private byte[] image; // Champ pour stocker l'image en tant que tableau de bytes

	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(	name = "shops_types",
			joinColumns = @JoinColumn(name = "id_user"),
			inverseJoinColumns = @JoinColumn(name = "id_shoptype"))
	private Set<ShopType> shopType = new HashSet<>();



	public Shop() {

	}
	
	public Shop(String email, String password, LocalDateTime d, String role) {
		super(email, password, d, role);
		}


	public Shop(String email, String password, LocalDateTime d, String role, String name, String location, String phone, String openingTime, String closingTime, Set<ShopType> shopType, byte[] image) {
		super(email, password, d, role);
		this.name = name;
		this.location = location;
		this.phone = phone;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.shopType = shopType;
		this.image = image;
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
	
	@OneToMany(mappedBy="shop", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonIgnore
	protected Set<Product> products = new HashSet<>();

	public Set<Product> getProducts() { return products; }
	public void addProduct(Product p) { this.products.add(p); }	
	public void removeProduct(Product p) { this.products.remove(p); }

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
