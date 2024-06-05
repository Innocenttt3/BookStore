package com.kamilG.service;

import com.kamilG.model.User;
import com.kamilG.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IUserService {

  Optional<User> findByUsername(String username);

  String registerUser(User user);
}
