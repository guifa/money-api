package com.guifa.money.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guifa.money.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
