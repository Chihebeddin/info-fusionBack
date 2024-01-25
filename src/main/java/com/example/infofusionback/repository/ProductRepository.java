package com.example.infofusionback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.infofusionback.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
	List<Product> findAll();
	
	Product findById(long id);
	
	@Query(value="select * from Product p where p.id_user =:id", nativeQuery=true)
	List<Product> findByIdUser(Long id);

}
