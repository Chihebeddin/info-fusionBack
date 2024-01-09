package com.example.infofusionback.service;

import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.EShopType;
import com.example.infofusionback.entity.ShopType;
import com.example.infofusionback.repository.ClientRepository;
import com.example.infofusionback.repository.ShopTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopTypeServiceImpl implements ShopTypeService {

    @Autowired
    private ShopTypeRepository shopTypeRepository;


    @Override
    public ShopType saveShopType(ShopType shopType) {
        return shopTypeRepository.save(shopType);
    }

    @Override
    public Boolean existsByType(EShopType Es) {
        return shopTypeRepository.existsByType(Es);
    }


}
