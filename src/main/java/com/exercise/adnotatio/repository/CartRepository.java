package com.exercise.adnotatio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.adnotatio.model.Cart;
import com.exercise.adnotatio.model.CartId;

public interface CartRepository extends JpaRepository<Cart, CartId> {
  // ambil semua item dalam keranjang milik customer tertentu
  List<Cart> findByCustomerId(Integer customerId);
  
  // cek apakah produk tertentu sudah ada di cart customer
  boolean existsByCustomerIdAndProductId(Integer customerId, Integer productId);
}
