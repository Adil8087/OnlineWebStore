package com.OnWeb.demo.reposiitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OnWeb.demo.model.Category;

public interface CategoryReporsitory extends JpaRepository<Category, Integer> {

}
