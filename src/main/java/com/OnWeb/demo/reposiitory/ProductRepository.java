package com.OnWeb.demo.reposiitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OnWeb.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
