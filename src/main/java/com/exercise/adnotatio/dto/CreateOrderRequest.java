package com.exercise.adnotatio.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
  @NotNull
  private Integer customerId;

  @NotNull
  private Integer deliveryId;

  @NotNull
  private Integer regionId; // untuk cek apakah delivery melayani region
}
