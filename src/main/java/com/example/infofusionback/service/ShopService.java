package com.example.infofusionback.service;

import java.util.List;

import com.example.infofusionback.entity.Shop;


public interface ShopService {

		Shop saveShop(Shop user);
		Shop getShopById(Long id);
	    List<Shop> getAllShops();
	    void deleteShop(Long id);
}
