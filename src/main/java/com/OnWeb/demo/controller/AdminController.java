package com.OnWeb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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


}
