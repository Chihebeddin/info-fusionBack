package com.example.infofusionback.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.infofusionback.entity.Shop;


@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {

	Shop findById(long id);

	List<Shop> findAll();

}
