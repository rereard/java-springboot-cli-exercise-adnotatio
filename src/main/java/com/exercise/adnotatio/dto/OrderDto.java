package com.exercise.adnotatio.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
  private Integer id;
  private LocalDate orderDate;
  private LocalDate arrivalDate;

  private Double deliveryFee;
  private Double totalAmount;

  private Integer customerId;
  private String customerName;

  private Integer deliveryId;
  private String deliveryName;

  private List<OrderDetailDto> orderDetails;
}
