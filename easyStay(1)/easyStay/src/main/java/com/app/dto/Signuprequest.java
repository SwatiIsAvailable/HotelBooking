package com.app.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.app.entities.ROLE;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Signuprequest {
	
    @NotBlank(message="address can't be blank")
	private String address;
	@Column(unique = true)
	
	@NotBlank(message = "Email must be supplied")
	@Email(message = "Invalid Email")
	private String email;
	
	@NotBlank(message = "name can't be blank")
	@Column(length = 25)
	private String name;
	
	@Column(length = 25)
	private String city;
	
	//@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Invalid password format")
	@Column(nullable = false)
	private String password;
	
	//@Enumerated(EnumType.STRING)
	private ROLE role;

}
