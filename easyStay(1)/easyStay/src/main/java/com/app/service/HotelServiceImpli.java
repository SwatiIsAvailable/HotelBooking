package com.app.service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.HotelDao;
import com.app.entities.Hotel;

@Service
@Transactional
public class HotelServiceImpli implements HotelService {

	@Autowired
	private HotelDao d;
	
	@Override
	public List<Hotel> getAllHotelDetails() {
		
		return d.findAll();
	}

	@Override
	public Hotel addHotelDetails(Hotel hm) {
		
		return d.save(hm);
	}

	@Override
	public Hotel updateHotelDetails(Hotel detachHm) {
		d.findById(detachHm.getId()).orElseThrow();
		return d.save(detachHm);
	}

	@Override
	public Optional<Hotel> getHotel(Long id) {
		
		return d.findById(id);
	}

	@Override
	public String deleteHotelDetails(Long id) {
		//d.findById(id).orElseThrow();
		d.deleteById(id);
		return "Successfully deleted!!";
	}

		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
