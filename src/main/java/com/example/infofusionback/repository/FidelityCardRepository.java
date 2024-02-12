package com.example.infofusionback.repository;

import com.example.infofusionback.entity.FidelityCard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FidelityCardRepository extends CrudRepository<FidelityCard, Long> {
	
	@Query(value="select * from Fidelity_Card f where f.id_user =:id", nativeQuery=true)
	FidelityCard findByIdUser(Long id);
	
}
