package com.kamilG.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String login(
      @RequestParam(value = "error", required = false) String errorMessage,
      @RequestParam(value = "logout", required = false) String logoutMessage,
      @RequestParam(value = "registerMessage", required = false) String registerMessage,
      Model model) {

    if (errorMessage != null) {
      model.addAttribute("errorMessage", "bledne dane");
    }

    if (logoutMessage != null) {
      model.addAttribute("logoutMessage", "wylogowano");
    }
    if (registerMessage != null) {
      model.addAttribute("registerMessage", registerMessage);
    }

    return "login";
  }
}
