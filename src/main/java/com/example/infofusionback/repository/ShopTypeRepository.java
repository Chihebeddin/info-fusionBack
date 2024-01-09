package com.example.infofusionback.repository;

import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.EShopType;
import com.example.infofusionback.entity.ShopType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopTypeRepository extends CrudRepository<ShopType, Long>{
	Boolean existsByType (EShopType Es);
	Optional<ShopType> findByType(EShopType type);


}
