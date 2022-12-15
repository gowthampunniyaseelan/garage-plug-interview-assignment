package com.example.demo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("users")
public class User {
  @Id

  private String id;

  private String username;
 
  private String email_id;

  private String password;

  private String confirm_password;
  
  private Integer order_count;

  private Integer discount;
  
  private String tier;

  public User(){

  }

  public User(String username, String email_id, String password, String confirm_password, Integer order_count, Integer discount, String tier){
    this.username=username;
    this.email_id=email_id;
    this.password=password;
    this.confirm_password=confirm_password;
    this.order_count = order_count;
    this.discount = discount;
    this.tier = tier;
  }
  
  // Get Method
  public String getUsername(){
    return this.username;
  }

  public String getEmailId(){
    return this.email_id;
  }

  public Integer getOrderCount(){
    return this.order_count;
  }

  public Integer getDiscount(){
    return this.discount;
  }
  
  public String getTier(){
    return this.tier;
  }


  // Set Method
  public void setUsername(String username){
    this.username = username;
  }

  public void setEmailId(String email_id){
    this.email_id = email_id;
  }

  public void setOrderCount(Integer order_count){
   this.order_count = order_count;
  }
  
  public void setDiscount(Integer discount){
    this.discount = discount;
   }

   public void setTier(String tier){
    this.tier = tier;
   }
}
