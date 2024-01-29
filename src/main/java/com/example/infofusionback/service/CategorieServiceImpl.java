package com.example.infofusionback.service;

import com.example.infofusionback.entity.Categorie;
import com.example.infofusionback.entity.Product;
import com.example.infofusionback.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategorieServiceImpl implements CategorieService {

    @Autowired
    protected CategorieRepository repo;

    @Override
    public List<Categorie> allCategories() {
        return repo.findAll();
    }

    @Override
    public Categorie getCategoryById(long id) {


        return repo.findById(id);
    }

    @Override
    public void addCategory(Categorie categorie) {
        repo.save(categorie);

    }

    @Override
    public void deleteCategory(long id) {
        repo.deleteById(id);

    }

    @Override
    public Categorie updateCategory(Long id, Categorie categorie) {
        Categorie cat = this.getCategoryById(id);
        cat.setLibelleCategorie(categorie.getLibelleCategorie());
        return repo.save(cat);
    }



    @Override
    public void addProductToCategory(Categorie categorie, Product product) {

      categorie.addProduct(product);
      repo.save(categorie);
    }



}
