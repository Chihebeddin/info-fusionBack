package com.example.infofusionback.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(uniqueConstraints = { 
		@UniqueConstraint(columnNames = "name") 
})
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_category")
	protected Long id;

	@Column
	private String name;

	public Category() {
	}

	public Category(String name) {
		this.name = name;
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

	@OneToMany(mappedBy = "category", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Product> products = new HashSet<Product>();

	public Set<Product> getProducts() {
		return this.products;

	}

	public void addProduct(Product p){
		products.add(p);
	}

	public void removeProduct(Product p){
		products.remove(p);
	}


}
