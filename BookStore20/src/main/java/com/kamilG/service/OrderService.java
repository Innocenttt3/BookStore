package com.kamilG.service;

import com.kamilG.model.*;
import com.kamilG.repository.OrderRepository;
import jakarta.transaction.Transactional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

  @Autowired private UserService userService;

  @Autowired private CartService cartService;

  @Autowired private OrderRepository orderRepository;

  @Override
  @Transactional
  public Order submitOrder() {
    User user = userService.getCurrentUser();
    Cart cart = user.getCart();

    Order order = new Order();
    order.setDate(new Date());
    order.setStatus(Order.OrderStatus.SUBMITTED);
    order.setUser(user);

    for (CartItem cartItem : cart.getItems()) {
      OrderItem orderItem = new OrderItem();
      orderItem.setBook(cartItem.getBook());
      orderItem.setQuantity(cartItem.getQuantity());
      order.getItems().add(orderItem);
    }
    cart.getItems().clear();
    cartService.saveCart(cart);
    return orderRepository.save(order);
  }

  @Override
  @Transactional
  public Order getOrderById(Long id) {
    return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
  }

  @Override
  @Transactional
  public void saveOrder(Order order) {
    orderRepository.save(order);
  }
}
