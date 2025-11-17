package com.exercise.adnotatio.dto;

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
public class CartDto {
  private Integer productId;
  private String productName;
  private Double price;
  private Integer quantity;
  private Double subtotal;
}
