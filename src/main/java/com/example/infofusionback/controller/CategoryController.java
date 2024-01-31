package com.example.infofusionback.controller;


import com.example.infofusionback.entity.Category;
import com.example.infofusionback.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {


	@Autowired
	protected CategoryService categoryService;


	@GetMapping("/{id}")
	public Category categoryById(@PathVariable long id) {
		return categoryService.getCategoryById(id);
	}

	@GetMapping()
	public List<Category> allCategories() {
		return categoryService.allCategories();
	}

	@PostMapping("/create")
	public Category createCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}

	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable long id) {
		this.categoryService.deleteCategory(id);
	}
}



