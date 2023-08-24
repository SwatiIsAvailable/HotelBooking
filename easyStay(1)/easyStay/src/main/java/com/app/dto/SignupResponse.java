package com.app.dto;

import com.app.entities.ROLE;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupResponse {
	private String address;
	
	private String email;
	
	private String name;
	
	private String city;
	
	private String password;
	
	private ROLE role;

}
