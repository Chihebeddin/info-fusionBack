package com.example.infofusionback.service;

import com.example.infofusionback.entity.Category;
import com.example.infofusionback.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	protected CategoryRepository repo;

	@Override
	public List<Category> allCategories() {
		return repo.findAll();
	}

	@Override
	public Category getCategoryById(long id) {


		return repo.findById(id);
	}

	@Override
	public Category addCategory(Category categorie) {
		return repo.save(categorie);

	}

	@Override
	public void deleteCategory(long id) {
		repo.deleteById(id);

	}



}
