package com.example.infofusionback.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_product")
	protected Long id;

	@Column
	protected String name;

	@Column
	protected double price;

	@Column 
	protected int quantity;

	public Product() {}

	public Product(String name, double price, int qte) {
		this.name = name;
		this.price = price;
		this.quantity = qte;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_user")
	protected Shop shop;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@OneToMany(mappedBy="id.product", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Contains> content = new HashSet<Contains>();
	public Set<Contains> getContent() {
		return this.content;
	}

	public void setContent(Set<Contains> p) {
		this.content = p;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_category")
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
