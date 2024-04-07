package com.example.infofusionback.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.infofusionback.entity.Shop;

@Service
public interface ShopService {

	Shop saveShop(Shop user);
	
	Shop getShopById(long id);
	
	List<Shop> getAllShops();
	
	void deleteShop(long id);
}
