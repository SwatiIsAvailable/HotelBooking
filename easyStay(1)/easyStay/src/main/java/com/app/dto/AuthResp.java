package com.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthResp {
	private Long userId;
	private String adrress;
	private String city;
	private String email;
	private String name;
	private String role;
	private String hotel_id;

}
