package com.exercise.adnotatio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exercise.adnotatio.model.Order;
import com.exercise.adnotatio.model.OrderDetail;
import com.exercise.adnotatio.model.OrderDetailId;
import com.exercise.adnotatio.repository.OrderDetailRepository;
import com.exercise.adnotatio.repository.OrderRepository;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Autowired
  private ProductService productService;

  @Autowired
  private CustomerService customerService;

  @Transactional
  public Order orderInsert(Order order, List<OrderDetail> orderDetails){
    Order insertedOrder = null;
    try {
      insertedOrder = orderRepository.save(order);
      for (OrderDetail od : orderDetails) {
        orderDetailRepository.save(new OrderDetail(new OrderDetailId(insertedOrder.getId(), od.getProduct().getId()), od.getUnitPrice(), od.getQuantity(), od.getProduct(), insertedOrder));
        productService.substractStock(od.getProduct(), od.getQuantity());
      }
      insertedOrder.getCustomer().substractBalance(insertedOrder.getTotalAmount());
      customerService.substractBalance(insertedOrder.getCustomer(), insertedOrder.getTotalAmount());
      System.out.println("Sukses menyimpan pemesanan");
    } catch (Exception e) {
      System.out.println("Terjadi error. Coba lagi || " + e.getMessage());
    }
    return insertedOrder;
  }
}
