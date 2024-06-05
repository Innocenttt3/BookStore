package com.kamilG.Controller;

import com.kamilG.model.Order;
import com.kamilG.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

  @Autowired private OrderService orderService;

  @PostMapping("/submit")
  public String submitOrder() {
    Order order = orderService.submitOrder();
    return "redirect:/order/" + order.getId();
  }

  @GetMapping("/{orderId}")
  public String getOrder(@PathVariable Long orderId, Model model) {
    Order order = orderService.getOrderById(orderId);
    model.addAttribute("order", order);
    return "order";
  }
}
