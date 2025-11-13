package com.exercise.adnotatio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.adnotatio.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
  
}
