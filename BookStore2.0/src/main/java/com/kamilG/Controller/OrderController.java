package com.kamilG.Controller;

import com.kamilG.Config.InsufficientStockException;
import com.kamilG.model.Order;
import com.kamilG.model.User;
import com.kamilG.service.OrderService;
import com.kamilG.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

  @Autowired private OrderService orderService;


  @PostMapping("/submit")
  public String submitOrder(RedirectAttributes redirectAttributes) {
    try {
      Order order = orderService.submitOrder();
      return "redirect:/order/" + order.getId();
    } catch (InsufficientStockException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
      return "redirect:/cart";
    }
  }

  @GetMapping("/{orderId}")
  public String getOrder(@PathVariable Long orderId, Model model) {
    Order order = orderService.getOrderById(orderId);
    model.addAttribute("order", order);
    return "order";
  }

  @GetMapping("/myOrders")
  public String getUserOrders(Model model) {
    List<Order> orders = orderService.getOrdersForCurrentUser();
    model.addAttribute("orders", orders);
    return "myOrders";
  }
}
