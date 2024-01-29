package com.example.infofusionback.repository;

import com.example.infofusionback.entity.Categorie;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategorieRepository extends CrudRepository <Categorie ,Long> {


    public List<Categorie> findAll() ;

    public Categorie findById(long id);

    //@Query(value = "select * from Categorie c where c.id_user =:id", nativeQuery = true)
    //List<Categorie> findByIdUser(Long id);


}
