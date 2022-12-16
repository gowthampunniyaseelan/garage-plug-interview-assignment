package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("users")
public class User {
  @Id

  private String id;

  private String username;
 
  private String emailId;

  private String password;

  private String confirmPassword;
  
  private Integer orderCount;

  private Integer discountPercentage;
  
  private String tier;


  public User(String username, String emailId, String password, String confirmPassword, Integer orderCount, Integer discountPercentage, String tier){
    this.username=username;
    this.emailId=emailId;
    this.password=password;
    this.confirmPassword = confirmPassword;
    if(orderCount == null){
      this.orderCount = 0;
    }else{
      this.orderCount = orderCount;
    }
    if(discountPercentage == null){
      this.discountPercentage = 0;
    }else{
      this.discountPercentage = discountPercentage;
    }
    this.tier = "regular";
  }
  
  // Get Method
  public String getUsername(){
    return this.username;
  }

  public String getEmailId(){
    return this.emailId;
  }

  public Integer getOrderCount(){
    return this.orderCount;
  }

  public Integer getDiscount(){
    return this.discountPercentage;
  }
  
  public String getTier(){
    return this.tier;
  }


  // Set Method
  public void setUsername(String username){
    this.username = username;
  }

  public void setEmailId(String emailId){
    this.emailId = emailId;
  }

  public void setOrderCount(Integer orderCount){
   this.orderCount = orderCount;
  }
  
  public void setDiscount(Integer discountPercentage){
    this.discountPercentage = discountPercentage;
   }

   public void setTier(String tier){
    this.tier = tier;
   }
}
