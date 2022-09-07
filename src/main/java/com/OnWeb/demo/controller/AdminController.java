package com.OnWeb.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.OnWeb.demo.model.Category;
import com.OnWeb.demo.service.CategoryService;

@Controller
public class AdminController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryService.getAllAttribute());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd" ;
	}
	
	@PostMapping("admin/categories/add")
	public String postCatAdd(@ModelAttribute("category")Category category) {
		categoryService.addCategory(category);
		
		return "redirect:/admin/categories" ;
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryService.RemoveCategorybyID(id);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable int id, Model model) {
			Optional<Category> category= categoryService.getCategoryByID(id);
			if(category.isPresent()) {
				model.addAttribute("category", category.get());
				return "categoriesAdd";
			}
		return "404";
	}
	
	// product section
	
	@GetMapping("/admin/products")
	public String getProduct(Model model) {
		
		
		return "products";
	}


}
