package com.example.infofusionback.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.infofusionback.entity.Product;
import com.example.infofusionback.entity.Shop;


@Service
public interface ProductService {

	List<Product> allProducts();

	Product getProductById(long id);
	
	List<Product> productsByShop(long id);

	void addProduct(Product product, Shop shop);

	void deleteProduct(long id);

	Product updateProduct(Long id, Product p);

}
