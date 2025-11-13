package com.exercise.adnotatio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.adnotatio.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
  
}
