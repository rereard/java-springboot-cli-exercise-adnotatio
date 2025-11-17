package com.exercise.adnotatio.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="Customers")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CustomerId")
  private Integer id;

  @Column(name = "FirstName", nullable=false, length=50)
  private String firstName;

  @Column(name = "LastName", nullable=false, length=50)
  private String lastName;

  @Column(name = "Balance", nullable=false)
  private double balance = 0;

  @Column(nullable=false, name="Deleted")
  private Boolean deleted = false;

  @OneToOne(mappedBy="customer", cascade=CascadeType.ALL)
  private Contact contact;

  public void substractBalance(double price){
    this.balance -= price;
  }
}
