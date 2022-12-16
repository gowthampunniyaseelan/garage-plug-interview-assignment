package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  @Mock
  UserRepository userRepository;

  private UserService userService;

  @Before
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
    userService = new UserService(userRepository);
  }

  @Test
  public void testGetUserByUsername(){
    Optional<User> user = createNewUser();
    Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);
    ResponseEntity response = userService.getUserByUsername("gowtham");
    assertEquals(HttpStatus.OK,response.getStatusCode());
  }

  @Test
  public void testGetUserByUsernameNull(){
    Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(null);
    ResponseEntity response = userService.getUserByUsername("gowtham");
    assertEquals(HttpStatus.OK,response.getStatusCode());
  }

  private Optional<User> createNewUser(){
    User user = new User("gowtham","abc.@gmail.com","abc","abc",8,10,"gold");
    return Optional.of(user);
  }
  
}
