package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;


@RestController
public class UserController {
  private UserService userService;
  
  @Autowired
  public UserController(UserService userService){
    this.userService = userService;
  }

  @PostMapping("user-management/user")
  public ResponseEntity<User> createUser(@RequestBody User user){
    return userService.createUser(user);
  }

  @GetMapping("order-management/order/{username}")
  public ResponseEntity getUserByUsername(@PathVariable String username){
    return userService.getUserByUsername(username);
}

}
