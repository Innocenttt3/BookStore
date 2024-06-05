package com.kamilG.service;

import com.kamilG.model.Cart;
import com.kamilG.model.Role;
import com.kamilG.model.User;
import com.kamilG.repository.RoleRepository;
import com.kamilG.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  @Autowired private UserRepository userRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private RoleRepository roleRepository;

  @Override
  @Transactional
  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  @Transactional
  public String registerUser(User user) {
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      return "failure";
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setCart(new Cart());
    Role role = roleRepository.findByName("USER").orElse(null);
    if (role != null) {
      user.getRoles().add(role);
    } else {
      Role newUserRole = new Role();
      newUserRole.setName("USER");
      user.getRoles().add(newUserRole);
    }
    userRepository.save(user);
    return "success";
  }

  @Transactional
  public void save(User user) {
    userRepository.save(user);
  }

  @Transactional
  public User getCurrentUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    return userRepository.findByUsername(username).orElse(null);
  }
}
