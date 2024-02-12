package com.example.infofusionback.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.infofusionback.entity.Contains;

public interface ContainsRepository extends CrudRepository<Contains, Long> {
	
	List<Contains> findAll();

}
