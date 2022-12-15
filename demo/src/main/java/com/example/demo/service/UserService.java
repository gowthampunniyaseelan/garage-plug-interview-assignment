package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  public ResponseEntity<List<User>> getUser(){
    return ResponseEntity.ok(this.userRepository.findAll());
  }
  
  public ResponseEntity<User> createUser(@RequestBody User user){
    return ResponseEntity.status(201).body(this.userRepository.save(user));
  }
  
  void sendMail(String mail){
    System.out.println(mail);
  }

  public void UpdateUser(Optional<User> user){
    User updateOrderDiscountTier = user.get();
    Integer discount = updateOrderDiscountTier.getDiscount();
    Integer order_count = updateOrderDiscountTier.getOrderCount();
    String tier = updateOrderDiscountTier.getTier();

    order_count = order_count + 1;
    
    if(order_count == 9){
      sendMail("You have placed 9 orders with us. Buy one more stuff and you will be promoted to Gold customer and enjoy 10% discounts!");
    }

    if(order_count == 19){
      sendMail("You have placed 19 orders with us. Buy one more stuff and you will be promoted to Platinum customer and enjoy 20% discounts!");
    }

    if(order_count == 10 || order_count > 10){
      if(order_count < 20){
        tier = "gold";
        discount = discount + 10;
      }
    }
    if(order_count == 20 || order_count > 20){
      tier = "platinum";
      discount = discount + 20;
    }

    updateOrderDiscountTier.setDiscount(discount);
    updateOrderDiscountTier.setOrderCount(order_count);
    updateOrderDiscountTier.setTier(tier);


    User result = this.userRepository.save(updateOrderDiscountTier);
  }

  public ResponseEntity getUserByEmail(@PathVariable String username){
    Optional<User> user = this.userRepository.findByUsername(username);
    if(user.isPresent()){
      UpdateUser(user);
      return ResponseEntity.ok(user.get());
    }else{
      return ResponseEntity.ok("The user: " + username + " doesn't exists.");
    }
  }

}
