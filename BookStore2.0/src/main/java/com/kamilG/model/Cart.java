package com.kamilG.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "cart")
  private User user;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> items = new ArrayList<>();

  public void addItem(Book book, int quantity) {
    boolean itemExists = false;
    for (CartItem cartItem : items) {
      if (cartItem.getBook().equals(book)) {
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        itemExists = true;
        break;
      }
    }
    if (!itemExists) {
      CartItem newItem = new CartItem();
      newItem.setCart(this);
      newItem.setBook(book);
      newItem.setQuantity(quantity);
      items.add(newItem);
    }
  }

  public void removeItem(Book book) {
    for (int i = 0; i < items.size(); i++) {
      CartItem cartItem = items.get(i);
      if (cartItem.getBook().equals(book)) {
        if (cartItem.getQuantity() > 1) {
          cartItem.setQuantity(cartItem.getQuantity() - 1);
        } else {
          items.remove(i);
        }
        break;
      }
    }
  }
}
