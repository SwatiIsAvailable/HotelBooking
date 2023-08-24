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

import com.app.entities.Room;
import com.app.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomService rms;
	
	public RoomController()
	{
		System.out.println("In ctor of Room COntroller");
	}
	
	@GetMapping("/")
	public List<Room> listAllRooms(){
		return rms.getAllRoomDetails();
	}
	
	@PostMapping
	public Room addNewHm(@RequestBody Room hm)
	{
		return rms.addRoomDetails(hm);
		
	}
	
	
	@GetMapping("/{rmId}")
	//@pathVariable => method arg level annotation, to bind incoming uri variable
	public Optional<Room> upd1(@PathVariable Long rmId){
		System.out.println("In get Hm Details: "+rmId);
		return rms.getRoom(rmId);
				//.orElseThrow(() -> new RessourceNotFoundException("Invalid Emp Id"));
	}
	
	@PutMapping
	public Room updateEmpDetails(@RequestBody Room rm)
	{
		System.out.println("In update emp"+rm.getId());
		return rms.updateRoomDetails(rm);	
	}
	
	@DeleteMapping("/{rmId}") // 
	public String deleteRoom(@PathVariable Long rmId) {
		System.out.println("in Delete Emp "+rmId);
		rms.deleteRoomDetails(rmId);
		return "Removed !";
	}
}
