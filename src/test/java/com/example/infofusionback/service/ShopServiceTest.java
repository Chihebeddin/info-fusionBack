package com.example.infofusionback.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.infofusionback.entity.Shop;

@SpringBootTest
public class ShopServiceTest {
	
	@Autowired
	protected ShopService service;
	
	protected long shopId;
	
	@Test
	public void testGetShopById() {
		Shop shop = service.getShopById(2);
		
		assertThat(shop).isNotNull();
		assertThat(shop.getId()).isEqualTo(2);
		assertThat(shop.getEmail()).isEqualTo("lefournil@gmail.com");
		assertThat(shop.getLocation()).isEqualTo("100 Boulevard Jean Lebas");
		
	}
	
	@Test
	public void testGetShopByIdNotOK() {
		Shop shop = service.getShopById(50);
		
		assertThat(shop).isNull();
	}
	
	
	@Test
	public void testSaveShop() {
		Shop shop = new Shop("auchan-v2@email.com", "test1234", LocalDateTime.now(), "ROLE_SHOP");
		shop.setName("Auchan V2");
		shop.setLocation("Centre commercial V2");
		
		Shop newShop = service.saveShop(shop);
		this.shopId = newShop.getId(); 
		
		assertThat(newShop).isEqualTo(shop);
		assertThat(newShop.getName()).isEqualTo("Auchan V2");
		assertThat(newShop.getEmail()).isEqualTo("auchan-v2@email.com");
		
	}
	
	@Test
	public void testDeleteShop() {
		assertThat(service.getShopById(this.shopId)).isNotNull();
		
		service.deleteShop(this.shopId);
		assertThat(service.getShopById(this.shopId)).isNull();
		
		
	}

}
