package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entities.Hotel;

public interface HotelService {
	public List<Hotel> getAllHotelDetails();
	
	Hotel addHotelDetails(Hotel hm);
	
	Hotel updateHotelDetails(Hotel hm);
	
	public Optional<Hotel> getHotel(Long id);
	
	String deleteHotelDetails(Long id);
	
}
