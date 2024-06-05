package com.kamilG.service;

import com.kamilG.model.Cart;

public interface ICartService {
  Cart getCurrentUserCart();

  Cart addToCart(long itemId, int quantity);

  void removeFromCart(long itemId);

  Cart saveCart(Cart cart);
}
