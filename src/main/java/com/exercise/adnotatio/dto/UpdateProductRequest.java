package com.exercise.adnotatio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
  @NotBlank
  private String name;

  @NotNull
  @Min(1)
  private Double price;

  @NotNull
  @Min(0)
  private Integer unitStock;

  private Boolean deactivated;
}
