package com.exercise.adnotatio.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name="Carts")
@ToString
public class Cart {
  @EmbeddedId
  private CartId cartId;

  @Column(name = "Quantity", nullable=false)
  private Integer quantity;

  @Column(name = "AddedDate", nullable=false)
  private LocalDateTime addedDate;

  @ManyToOne
  @MapsId("customerId")
  @JoinColumn(name="CustomerId")
  private Customer customer;

  @ManyToOne
  @MapsId("productId")
  @JoinColumn(name="ProductId")
  private Product product;
}
