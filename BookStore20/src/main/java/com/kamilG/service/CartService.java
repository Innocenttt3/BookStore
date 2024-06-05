package com.kamilG.service;

import com.kamilG.model.Book;
import com.kamilG.model.Cart;
import com.kamilG.model.User;
import com.kamilG.repository.BookRepository;
import com.kamilG.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {
  @Autowired private CartRepository cartRepository;
  @Autowired private BookRepository bookRepository;
  @Autowired private UserService userService;

  @Override
  @Transactional
  public Cart getCurrentUserCart() {
    User currentUser = userService.getCurrentUser();
    return currentUser.getCart();
  }

  @Override
  @Transactional
  public Cart addToCart(long itemId, int quantity) {
    Cart cart = getCurrentUserCart();
    Book book =
        bookRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Book not found"));
    cart.addItem(book, quantity);
    return saveCart(cart);
  }

  @Override
  @Transactional
  public void removeFromCart(long itemId) {
    Cart cart = getCurrentUserCart();
    Book book =
        bookRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Book not found"));
    cart.removeItem(book);
  }

  @Override
  @Transactional
  public Cart saveCart(Cart cart) {
    return cartRepository.save(cart);
  }
}
