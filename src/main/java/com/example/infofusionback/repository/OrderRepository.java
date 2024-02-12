package com.example.infofusionback.repository;

import com.example.infofusionback.entity.OrderEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Long > {

	OrderEntity findById(long id);
	
	@Query(value="select * from Order_Entity o where o.id_user =:id order by validation_date desc", nativeQuery=true)
	List<OrderEntity> findByIdUser(long id);
	
	@Query(value="select o.id_order_entity, o.id_user, total, collect_date, payment_option, status, validation_date from Order_Entity o "
			+ "join Contains c on o.id_order_entity = c.id_order_entity "
			+ "join Product p on c.id_product = p.id_product "
			+ "where p.id_user =:id group by o.id_order_entity order by validation_date", 
			nativeQuery=true)
	List<OrderEntity> findAll(long id);

	List<OrderEntity> findAll();


}
