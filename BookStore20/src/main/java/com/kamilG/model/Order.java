package com.kamilG.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> items = new ArrayList<>();

  private Date date;

  private OrderStatus status;

  public enum OrderStatus {
    SUBMITTED,
    PAID,
    SHIPPED,
    COMPLETED
  }
}
