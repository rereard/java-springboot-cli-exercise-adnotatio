package com.exercise.adnotatio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.adnotatio.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  @Override
  Page<Product> findAll(Pageable pageable);  
}
