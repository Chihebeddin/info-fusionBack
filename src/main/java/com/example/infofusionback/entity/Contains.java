package com.example.infofusionback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Contains {

	@EmbeddedId
	@JsonIgnore
	private ContainsId id;
	
	public Contains() {
		
	}
	
	public Contains(Product p, OrderEntity o, int qte) {
		this.id = new ContainsId();
		this.id.setOrder(o);
		this.id.setProduct(p);
		this.quantity = qte;
	}

	public ContainsId getId() {
		return id;
	}

	public void setId(ContainsId id) {
		this.id = id;
	}
	
	@Column
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderEntity getOrder(){
		return getId().getOrder();
	}
	
	public void setOrder(OrderEntity order){
		getId().setOrder(order);
	}

	public Product getProduct(){
		return getId().getProduct();
	}
	
	public void setProduct (Product product){
		getId().setProduct(product);
	}

}
