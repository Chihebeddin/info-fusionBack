package com.example.infofusionback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ContainsId {

	@ManyToOne
	@JoinColumn(name = "id_order_entity")
	@JsonIgnore
	private OrderEntity orderEntity;

	@ManyToOne
	@JoinColumn(name="id_product")
	@JsonIgnore
	private Product product;

	public OrderEntity getOrder() {
		return orderEntity;
	}

	public void setOrder(OrderEntity order) {
		this.orderEntity = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
