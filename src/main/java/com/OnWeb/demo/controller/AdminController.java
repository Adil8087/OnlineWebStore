package com.OnWeb.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.OnWeb.demo.dto.ProductDTO;
import com.OnWeb.demo.model.Category;
import com.OnWeb.demo.model.Product;
import com.OnWeb.demo.service.CategoryService;
import com.OnWeb.demo.service.ProductService;

@Controller
public class AdminController {

	public static String uploadDir= System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;

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
		return "categoriesAdd";
	}

	@PostMapping("admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);

		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryService.RemoveCategorybyID(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable int id, Model model) {
		Optional<Category> category = categoryService.getCategoryByID(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}
		return "404";
	}

	// product section

	@GetMapping("/admin/products")
	public String getProduct(Model model) {

		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}

	@GetMapping("/admin/products/add")
	public String getProductAdd(Model model) {

		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllAttribute());
		return "productsAdd";
	}

	@PostMapping("/admin/products/add")
	public String putProductAdd(@ModelAttribute("productDTO") ProductDTO productDTO,
			@RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imgName)
			throws IOException {

		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		Optional<Category> category = categoryService.getCategoryByID(productDTO.getCategoryId());
		
		product.setCategory(category.get());
		
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(product.getDescription());

		String imageUUID;
		if (!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}else {
			imageUUID=imgName;
		}
		product.setImageName(imageUUID);
		productService.addProduct(product);
		
		return "redirect:/admin/products";
	}

}
