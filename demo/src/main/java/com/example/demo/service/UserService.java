package com.example.demo.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
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
  
  public ResponseEntity<User> createUser(@RequestBody User user){
    return ResponseEntity.status(201).body(this.userRepository.save(user));
  }
  
  void sendMail(String mail){
    System.out.println(mail);
  }

  public void UpdateUser(Optional<User> user){
    User updateOrderDiscountTier = user.get();
    Integer discountPercentage = updateOrderDiscountTier.getDiscount();
    Integer orderCount = updateOrderDiscountTier.getOrderCount();
    String tier = updateOrderDiscountTier.getTier();

    orderCount = orderCount + 1;
    
    if(orderCount == 9){
      sendMail("You have placed 9 orders with us. Buy one more stuff and you will be promoted to Gold customer and enjoy 10% discounts!");
    }

    if(orderCount == 19){
      sendMail("You have placed 19 orders with us. Buy one more stuff and you will be promoted to Platinum customer and enjoy 20% discounts!");
    }

    if(orderCount == 10 || orderCount > 10){
      if(orderCount < 20){
        tier = "gold";
        discountPercentage = discountPercentage + 10;
      }
    }
    if(orderCount == 20 || orderCount > 20){
      tier = "platinum";
      discountPercentage = discountPercentage + 20;
    }

    updateOrderDiscountTier.setDiscount(discountPercentage);
    updateOrderDiscountTier.setOrderCount(orderCount);
    updateOrderDiscountTier.setTier(tier);


    User result = this.userRepository.save(updateOrderDiscountTier);
  }

  public ResponseEntity getUserByUsername(@PathVariable String username){
    Optional<User> user = this.userRepository.findByUsername(username);
    if(Objects.nonNull(user) && user.isPresent()){
      UpdateUser(user);
      return ResponseEntity.ok(user.get());
    }else{
      return ResponseEntity.ok("The user " + username + " doesn't exists.");
    }
  }

}
