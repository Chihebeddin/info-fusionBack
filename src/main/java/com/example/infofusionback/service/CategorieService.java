package com.example.infofusionback.service;


import com.example.infofusionback.entity.Categorie;
import com.example.infofusionback.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategorieService {

        List<Categorie> allCategories();

        Categorie getCategoryById(long id);

        void addCategory(Categorie categorie);

        void deleteCategory(long id);

        Categorie updateCategory(Long id, Categorie categorie);

        void addProductToCategory(Categorie categorie, Product product);


    }


