package com.OnWeb.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnWeb.demo.model.Product;
import com.OnWeb.demo.reposiitory.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public void addProduct(Product product)
	{
		productRepository.save(product);
	}
	public void removeProduct(Integer id) {
		productRepository.deleteById(id);
	}
	public Optional<Product> getProduct(Integer id){
		return productRepository.findById(id);
	}
	
	public List<Product> getAllProductsByCategoryId(int id){
		return productRepository.findAllByCategory_Id(id);
	}
}
