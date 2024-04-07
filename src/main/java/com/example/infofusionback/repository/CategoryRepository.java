package com.example.infofusionback.repository;

import com.example.infofusionback.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository <Category ,Long> {


    public List<Category> findAll() ;

    public Category findById(long id);
    
    public Category findByName(String name);


}
