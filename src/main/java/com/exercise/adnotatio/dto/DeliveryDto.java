package com.exercise.adnotatio.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryDto {
  private Integer id;
  private String name;
  private Double cost;
}
