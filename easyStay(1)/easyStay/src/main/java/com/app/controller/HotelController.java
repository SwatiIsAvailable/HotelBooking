package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Hotel;
import com.app.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelService hms;
	
	public HotelController()
	{
		System.out.println("In ctor of "+getClass());
	}
	
	@GetMapping("/")
	public List<Hotel> listAllHotel(){
		return hms.getAllHotelDetails();
	}
	
	@PostMapping
	public Hotel addNewHotel(@RequestBody Hotel hm)
	{
		return hms.addHotelDetails(hm);
		
	}
	
	
	@GetMapping("/{hmId}")
	//@pathVariable => method arg level annotation, to bind incoming uri variable
	public Optional<Hotel> upd1(@PathVariable Long hmId){
		System.out.println("In get Hm Details: "+hmId);
		return hms.getHotel(hmId);
				//.orElseThrow(() -> new RessourceNotFoundException("Invalid Emp Id"));
	}
	
	@PutMapping
	public Hotel updateEmpDetails(@RequestBody Hotel hm)
	{
		System.out.println("In update emp"+hm.getId());
		return hms.updateHotelDetails(hm);	
	}
	
	@DeleteMapping("/{hmId}") // 
	public String deleteHotel(@PathVariable Long hmId) {
		System.out.println("in Delete Emp "+hmId);
		hms.deleteHotelDetails(hmId);
		return "Removed !";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
