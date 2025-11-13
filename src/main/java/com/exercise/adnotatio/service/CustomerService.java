package com.exercise.adnotatio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.adnotatio.model.Customer;
import com.exercise.adnotatio.repository.CustomerRepository;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  public List<Customer> getAllCustomers(){
    return customerRepository.findAll();
  }  

  public Customer findCustomertById(Integer id){
    return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer dengan ID "+ id + " tidak ditemukan\n"));
  }

  public void substractBalance(Customer customer, Double price){
    Customer selectedCustomer = customerRepository.findById(customer.getId()).orElseThrow(() -> new RuntimeException("Product dengan ID "+ customer.getId() + " tidak ditemukan"));
    selectedCustomer.substractBalance(price);
    customerRepository.save(selectedCustomer);
  }
}
