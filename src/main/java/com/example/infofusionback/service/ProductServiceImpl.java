package com.example.infofusionback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.infofusionback.entity.Product;
import com.example.infofusionback.entity.Shop;
import com.example.infofusionback.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	protected ProductRepository repo;

	@Override
	public List<Product> allProducts() {
		return repo.findAll();
	}

	@Override
	public Product getProductById(long id) {
		return repo.findById(id);
	}

	@Override
	public void addProduct(Product product, Shop shop) {
		product.setShop(shop);
		shop.addProduct(product);
		repo.save(product);

	}

	@Override
	public void deleteProduct(long id) {
		this.repo.deleteById(id);

	}

	@Override
	public Product updateProduct(Long id, Product p) {
		Product prd = this.getProductById(id);
		prd.setName(p.getName());
		prd.setPrice(p.getPrice());
		prd.setQuantity(p.getQuantity());
		return repo.save(prd);
	}

	@Override
	public List<Product> productsByShop(long id) {
		return repo.findByIdUser(id);
	}

}
