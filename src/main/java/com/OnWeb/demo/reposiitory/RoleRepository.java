package com.OnWeb.demo.reposiitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OnWeb.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
