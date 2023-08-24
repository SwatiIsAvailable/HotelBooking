package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthResponse;
import com.app.dto.LoginForm;
import com.app.service.UserService;

@RestController
@RequestMapping("/login")
public class AuthController {
     @Autowired 
     private UserService userService;

     @PostMapping
     public ResponseEntity<?> login(@RequestBody LoginForm loginForm)
     {
    	 if(userService.authenticate(loginForm.getEmail() , loginForm.getPassword()))
    	 {
    		String userRole = userService.getUserRole();
    		return ResponseEntity.ok(new AuthResponse(userRole));
    	 }else {
    		 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    	 }
     }
     //-----------------------
     
} 
