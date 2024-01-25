package com.example.infofusionback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.infofusionback.entity.Product;
import com.example.infofusionback.entity.Shop;
import com.example.infofusionback.service.ProductService;
import com.example.infofusionback.service.ShopService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	protected ProductService productService;
	
	@Autowired
	protected ShopService shopService;
	
	@GetMapping("/{id}")
	public Product productById(@PathVariable long id) {
		return productService.getProductById(id);
	}
	
	@GetMapping("/filtered")
	public List<Product> productByUser(@RequestParam(value="shop") long id) {
		return productService.productsByShop(id);
	}
	
	@GetMapping()
	public List<Product> products() {
		return productService.allProducts();
	}
	
	@PostMapping("/create")
	public Product createProduct(@RequestBody Product product, @RequestParam(value="shop")long shopId) {
		Shop shop = shopService.getShopById(shopId);		
		productService.addProduct(product, shop);
		
		return product;
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product p) {
		return this.productService.updateProduct(id, p);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable long id) {
		this.productService.deleteProduct(id);
	}


}
