package com.kamilG.Controller;

import com.kamilG.model.User;
import com.kamilG.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

  @Autowired private UserService userService;

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/register")
  public String registerUser(
      @ModelAttribute("user") User user,
      @RequestParam(value = "message", required = false) String message,
      RedirectAttributes redirectAttributes,
      Model model) {
    if (message!=null) {
      model.addAttribute("message", message);
    }
    String result = userService.registerUser(user);
    model.addAttribute("message", result);
    redirectAttributes.addAttribute("registerMessage", result);
    if (result.equals("success")) {
      return "redirect:/login";
    }
    return "register";
  }
}
