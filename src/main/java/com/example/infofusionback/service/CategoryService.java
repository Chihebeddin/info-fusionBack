package com.example.infofusionback.service;


import com.example.infofusionback.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

	List<Category> allCategories();

	Category getCategoryById(long id);
	
	Category getCategoryByName(String name);

	Category addCategory(Category categorie);

	void deleteCategory(long id);


}


