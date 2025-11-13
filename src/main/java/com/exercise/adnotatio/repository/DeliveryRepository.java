package com.exercise.adnotatio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.adnotatio.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

  
}