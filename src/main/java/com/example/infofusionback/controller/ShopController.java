package com.example.infofusionback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

	@GetMapping("/{id}/image")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
		System.out.println("hello");
		Shop shopOptional = shopService.getShopById(id);
		System.out.println("hello "+shopOptional);
		if (shopOptional != null) {
			System.out.println("I'm in");
			byte[] imageBytes = shopOptional.getImage();

			// You might also want to set content type here based on your image type
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			headers.setContentLength(imageBytes.length);
			return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public List<Shop> getAllShops() {
		return shopService.getAllShops();
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