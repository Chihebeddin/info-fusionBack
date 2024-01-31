package com.example.infofusionback.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ContainsId {

	@ManyToOne
	@JoinColumn(name = "id_order")
	private OrderEntity order;

	@ManyToOne
	@JoinColumn(name="id_product")
	private Product product;

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
