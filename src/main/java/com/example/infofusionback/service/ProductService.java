package com.example.infofusionback.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.infofusionback.entity.Category;
import com.example.infofusionback.entity.Product;
import com.example.infofusionback.entity.Shop;

@Service
public interface ProductService {

	List<Product> allProducts();

	Product getProductById(long id);
	
	List<Product> productsByShop(long id);

	Product addProduct(Product product, Shop shop, Category ctg);

	void deleteProduct(Long id);

	Product updateProduct(Long id, Product p);
	
	Product updateStock(long id, int qte);

}
