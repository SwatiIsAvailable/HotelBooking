package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.BookingTbl;

public interface BookingDao extends JpaRepository<BookingTbl,Long> {
//	select id from room where hotel_id in(select id from hotel where city = "XYZ");
}
