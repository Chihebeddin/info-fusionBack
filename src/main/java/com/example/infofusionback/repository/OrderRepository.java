package com.example.infofusionback.repository;

import com.example.infofusionback.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Long > {

	OrderEntity findById(long id);


	List<OrderEntity> findAll();


}
