package com.exercise.adnotatio.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "Regions")
@ToString
public class Region {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "RegionId")
  private Integer id;

  @Column(name = "RegionName", nullable = false)
  private String name;

  @ManyToMany(mappedBy = "regions")
  private Set<Delivery> deliveries;
}
