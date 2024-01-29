package com.example.infofusionback.repository;

import com.example.infofusionback.entity.Panier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PanierRepository extends CrudRepository<Panier, Long > {

    Panier findById(long id);


    List<Panier> findAll();


}
