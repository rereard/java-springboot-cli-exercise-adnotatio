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
public class OrderDetailDto {
  private Integer productId;
  private String productName;
  private Double unitPrice;
  private Integer quantity;
  private Double subtotal;
}
