package com.exercise.adnotatio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
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
@Table(name="Contacts")
public class Contact {
  @Id
  @Column(name = "ContactId")
  private Integer id;

  @Column(name = "PhoneNumber")
  private String phoneNumber;

  @Column(name = "Email")
  private String email;

  @OneToOne
  @MapsId
  @JoinColumn(name="CustomerId")
  private Customer customer;
}
