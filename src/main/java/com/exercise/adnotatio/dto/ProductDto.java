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
public class ProductDto {
  private Integer id;
  private String name;
  private Double price;
  private Integer unitStock;
  private Boolean deactivated;
}
