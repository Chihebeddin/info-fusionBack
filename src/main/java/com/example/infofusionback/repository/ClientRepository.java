package com.example.infofusionback.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.infofusionback.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{
	
	Client findById(long id);
	
	List<Client> findAll();

}
