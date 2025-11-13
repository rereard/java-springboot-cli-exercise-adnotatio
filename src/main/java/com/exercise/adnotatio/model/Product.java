package com.exercise.adnotatio.model;

import java.text.NumberFormat;
import java.util.Locale;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Products")
public class Product {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "ProductId")
  private Integer id;

  @Column(name = "ProductName", nullable=false)
  private String name;

  @Column(name = "Price", nullable=false)
  private Double price;

  @Column(name = "UnitStock", nullable=false)
  private Integer unitStock;

  @Column(name = "Deactivated", nullable=false)
  private Boolean deactivated;

  public void substractStock(int qty){
    this.unitStock -= qty;
  }

  @Override
  public String toString() {
      NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
      String status = this.deactivated ? "Unlisted" : "Listed";
      return String.format("[%s] %s - %s - Stock: %d - %s", id, name, formatter.format(price), unitStock, status);
  }

  public String getFormatedPrice(){
    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    return formatter.format(price);
  }
}
