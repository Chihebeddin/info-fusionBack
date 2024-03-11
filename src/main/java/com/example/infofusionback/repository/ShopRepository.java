package com.example.infofusionback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.infofusionback.entity.Shop;


@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {

	Shop findById(long id);

	@Query(value="select s from Shop s "
			+ "join Product p on s.id = p.shop.id")
	List<Shop> findAll();

}
