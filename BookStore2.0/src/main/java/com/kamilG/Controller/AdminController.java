package com.kamilG.Controller;

import com.kamilG.model.Order;
import com.kamilG.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired private OrderService orderService;

  @GetMapping("/adminView")
  public String adminpanel() {
    return "adminView";
  }

  @GetMapping("/orders")
  public String viewAllOrders(Model model) {
    List<Order> orders = orderService.getAllOrders();
    model.addAttribute("orders", orders);
    return "adminPanel";
  }

  @PostMapping("/changeOrderStatus")
  public String changeOrderStatus(
      @RequestParam("orderId") Long orderId, @RequestParam("status") String status) {
    orderService.updateOrderStatus(orderId, Order.OrderStatus.valueOf(status));
    return "redirect:/admin/orders";
  }

  @PostMapping("/deleteOrder")
  public String deleteOrder(@RequestParam("orderId") Long orderId) {
    orderService.deleteOrderById(orderId);
    return "redirect:/admin/orders";
  }
}
