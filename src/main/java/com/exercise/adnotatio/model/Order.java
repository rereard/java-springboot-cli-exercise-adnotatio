package com.exercise.adnotatio.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Orders")
@ToString
public class Order {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "OrderId")
  private Integer id;

  @Column(name = "OrderDate")
  private LocalDate orderDate;

  @Column(name = "ArrivalDate")
  private LocalDate arrivalDate;
  
  @Column(name = "DeliveryFee")
  private Double deliveryFee;

  @Column(name = "TotalAmount")
  private Double totalAmount;

  @ManyToOne
  @JoinColumn(name="DeliveryId")
  private Delivery delivery;

  @ManyToOne
  @JoinColumn(name="CustomerId")
  private Customer customer;
}
