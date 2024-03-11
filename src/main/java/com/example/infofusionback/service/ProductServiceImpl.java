package com.example.infofusionback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.infofusionback.entity.Category;
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
	public Product addProduct(Product product, Shop shop, Category ctg) {
		product.setShop(shop);
		product.setCategory(ctg);
		
		shop.addProduct(product);
		ctg.addProduct(product);
		
		return repo.save(product);

	}

	@Override
	public void deleteProduct(long id) {
		repo.deleteById(id);

	}

	@Override
	public Product updateProduct(Long id, Product p) {
		Product prd = this.getProductById(id);
		prd.setName(p.getName());
		prd.setPrice(p.getPrice());
		prd.setSafetyStock(p.getSafetyStock());
		prd.setCategory(p.getCategory());
		return repo.save(prd);
	}

	@Override
	public List<Product> productsByShop(long id) {
		return repo.findByIdUser(id);
	}

	@Override
	public Product updateStock(long id, int qte) {
		Product prd = this.getProductById(id);
		
		prd.setQuantity(qte);
		return repo.save(prd);
	}


}
