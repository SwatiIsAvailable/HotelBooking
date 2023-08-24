package com.app.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookingTbl extends BaseEntity {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkIn;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkOut;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;
	@NotNull
	private int prize;
	@OneToOne
	@JoinColumn(name = "room_id")	
	private Room rm;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User us;
	public BookingTbl(LocalDate checkIn, LocalDate checkOut, LocalDate bookingDate, @NotNull int prize) {
		super();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.bookingDate = bookingDate;
		this.prize = prize;
	}
}
