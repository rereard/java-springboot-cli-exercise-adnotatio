package com.exercise.adnotatio.model;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Deliveries")
public class Delivery {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "DeliveryId")
  private Integer id;

  @Column(name = "DeliveryName", nullable=false)
  private String name;

  @Column(name = "Cost", nullable=false)
  private Double cost;

  @ManyToMany
  @JoinTable(name="DeliveriesRegions", joinColumns=@JoinColumn(name="DeliveryId"), inverseJoinColumns=@JoinColumn(name="RegionId"))
  private Set<Region> regions;

  @Override
  public String toString() {
    return String.format("[%s] %s - Biaya: %s", id, name, getFormatedPrice());
  }

  public String getFormatedPrice(){
    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    return formatter.format(cost);
  }
}
