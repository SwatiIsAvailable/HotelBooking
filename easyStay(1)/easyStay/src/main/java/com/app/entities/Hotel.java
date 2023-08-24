package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Hotel extends BaseEntity {
	@Column(length = 30)
	private String name;
	@Column(length = 50)
	private String address;
	@Column(length = 20)
	private String city;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "myHotel", orphanRemoval = true)
	private List<User> users = new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "myHotel", orphanRemoval = true)
	private List<Room> room = new ArrayList<>();
}