package com.exercise.adnotatio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.adnotatio.model.Delivery;
import com.exercise.adnotatio.repository.DeliveryRepository;

@Service
public class DeliveryService {
  @Autowired
  private DeliveryRepository deliveryRepository;

  public List<Delivery> getAllDeliveries(){
    return deliveryRepository.findAll();
  }

  public Delivery findDeliveryById(Integer id){
    return deliveryRepository.findById(id).orElseThrow(() -> new RuntimeException("Delivery dengan ID "+ id + " tidak ditemukan\n"));
  }
}
