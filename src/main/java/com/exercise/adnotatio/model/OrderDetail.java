package com.exercise.adnotatio.model;

import java.text.NumberFormat;
import java.util.Locale;

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
@Table(name="OrderDetails")
@ToString
public class OrderDetail {

  @EmbeddedId
  private OrderDetailId orderDetailId;

  @Column(name = "UnitPrice")
  private Double unitPrice;

  @Column(name = "Quantity")
  private Integer quantity;
  
  @ManyToOne
  @MapsId("productId")
  @JoinColumn(name="ProductId")
  private Product product;

  @ManyToOne
  @MapsId("orderId")
  @JoinColumn(name="OrderId")
  private Order order;

  public Double getTotalPrice(){
    return quantity * unitPrice;
  }

  public String getFormatedPrice(){
    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    return formatter.format(unitPrice);
  }

  public String getFormatedTotalPrice(){
    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    return formatter.format(quantity*unitPrice);
  }
}
