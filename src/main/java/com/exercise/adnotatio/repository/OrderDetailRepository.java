package com.exercise.adnotatio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.adnotatio.model.OrderDetail;
import com.exercise.adnotatio.model.OrderDetailId;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId>{
  // ambil semua detail per order
  List<OrderDetail> findByOrderId(Integer orderId);
}
