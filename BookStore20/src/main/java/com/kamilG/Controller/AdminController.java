package com.kamilG.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
  @GetMapping("/admin/adminView")
  public String adminpanel() {
    return "adminView";
  }
}
