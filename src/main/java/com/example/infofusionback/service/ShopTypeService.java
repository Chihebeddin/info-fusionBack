package com.example.infofusionback.service;

import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.EShopType;
import com.example.infofusionback.entity.ShopType;

import java.util.List;

public interface ShopTypeService {
	ShopType saveShopType(ShopType shopType);

	Boolean existsByType (EShopType Es);

}
