package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.RoomDao;
import com.app.entities.Room;

@Service
@Transactional
public class RoomServiceImpli implements RoomService{

	@Autowired
	private RoomDao r;
	
	@Override
	public List<Room> getAllRoomDetails() {
		
		return r.findAll();
	}

	@Override
	public Room addRoomDetails(Room rm) {
		
		return r.save(rm);
	}

	@Override
	public Room updateRoomDetails(Room rm) {
		r.findById(rm.getId()).orElseThrow();
		return r.save(rm);
	}

	@Override
	public Optional<Room> getRoom(Long id) {
		
		return r.findById(id);
	}

	@Override
	public String deleteRoomDetails(Long id) {
//		r.findById(id).orElseThrow();
		r.deleteById(id);
		return "Deleted!";
	}

}
