package com.example.infofusionback.repository;

import com.example.infofusionback.entity.OrderEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Long > {

	OrderEntity findById(long id);
	
	@Query(value="select * from Order_Entity o where o.id_user =:id", nativeQuery=true)
	List<OrderEntity> findByIdUser(long id);

	List<OrderEntity> findAll();


}
