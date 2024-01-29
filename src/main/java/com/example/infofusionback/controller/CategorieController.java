package com.example.infofusionback.controller;


import com.example.infofusionback.entity.Categorie;
import com.example.infofusionback.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategorieController {


    @Autowired
    protected CategorieService categorieService;


    @GetMapping("/{id}")
    public Categorie categorieById(@PathVariable long id) {
        return categorieService.getCategoryById(id);
    }

    @GetMapping("/all")
    public List<Categorie> allCategories() {
        return categorieService.allCategories();
    }

    @PostMapping("/create")
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        categorieService.addCategory(categorie);
        return categorie;
    }

    @PutMapping("/{id}")
    public Categorie updateCategorie(@PathVariable Long id, @RequestBody Categorie c) {
        return this.categorieService.updateCategory(id, c);
    }

    @DeleteMapping("/{id}")
    public void deleteCategorie(@PathVariable long id) {
        this.categorieService.deleteCategory(id);
    }
}



