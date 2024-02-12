package com.example.infofusionback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.infofusionback.entity.Contains;

public interface ContainsRepository extends CrudRepository<Contains, Long> {
	
	List<Contains> findAll();
	
	@Query(value="select * from Contains c where c.id_order_entity =:id", nativeQuery=true)
	List<Contains> findByIdOrderEntity(long id);

}
