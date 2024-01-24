package com.example.infofusionback.repository;

import com.example.infofusionback.entity.FidelityCard;
import com.example.infofusionback.entity.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FidelityCardRepository extends CrudRepository<FidelityCard, Long> {
}
