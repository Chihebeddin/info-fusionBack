package com.example.infofusionback.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.infofusionback.entity.Shop;
import com.example.infofusionback.service.ShopService;

@RestController
@RequestMapping("/shops")
public class ShopController {

	private final ShopService shopService;

	@Autowired
	public ShopController(ShopService shopService) {
		this.shopService = shopService;
	}

	@PostMapping
	public Shop createShop(@RequestBody Shop shop) {
		return shopService.saveShop(shop);
	}

	@GetMapping("/{id}")
	public Shop getShopById(@PathVariable Long id) {
		return shopService.getShopById(id);
	}

	@GetMapping
	public List<Shop> getAllShops() {
		return shopService.getAllShops();
	}
	
	@GetMapping("/filtered")
	public List<Shop> filteredShops() {
		List<Shop> shops = new ArrayList<>();
		for (Shop s : this.getAllShops()) {
			if ( !s.getProducts().isEmpty() ) {
				shops.add(s);
			}
		}
		return shops;
	}

	@PutMapping("/{id}")
	public Shop updateShop(@PathVariable Long id, @RequestBody Shop shop) {
		shop.setId(id);
		return shopService.saveShop(shop);
	}

	@DeleteMapping("/{id}")
	public void deleteShop(@PathVariable Long id) {
		shopService.deleteShop(id);
	}
}