package com.OnWeb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.OnWeb.demo.model.Category;
import com.OnWeb.demo.service.CategoryService;
import com.OnWeb.demo.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@GetMapping({"/","/home"})
	public String home(Model model)
	{
		model.addAttribute("categories", categoryService.getAllAttribute());
		model.addAttribute("products",productService.getAllProduct());
		return "shop";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllAttribute());
		model.addAttribute("products",productService.getAllProduct());
	
		return "shop";
	}
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(@PathVariable int id,Model model) {
		model.addAttribute("categories", categoryService.getAllAttribute());
		model.addAttribute("products",productService.getAllProductsByCategoryId(id));
	
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable int id,Model model) {
		model.addAttribute("product", productService.getProduct(id).get());
		return "viewProduct";
	}
	
	
}