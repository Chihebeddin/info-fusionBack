package com.example.infofusionback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.infofusionback.entity.Shop;


@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

}
