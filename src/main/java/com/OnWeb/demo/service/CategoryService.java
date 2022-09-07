package com.OnWeb.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnWeb.demo.model.Category;
import com.OnWeb.demo.reposiitory.CategoryReporsitory;

@Service
public class CategoryService {

	@Autowired
	CategoryReporsitory categoryReporsitory;
	
	public void addCategory(Category category) {
		categoryReporsitory.save(category);
	}
	
	public List<Category> getAllAttribute(){
		return categoryReporsitory.findAll();
	}
}
