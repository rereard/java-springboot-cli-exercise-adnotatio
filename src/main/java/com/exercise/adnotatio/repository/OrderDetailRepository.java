package com.exercise.adnotatio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.adnotatio.model.OrderDetail;
import com.exercise.adnotatio.model.OrderDetailId;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId>{
  
}
