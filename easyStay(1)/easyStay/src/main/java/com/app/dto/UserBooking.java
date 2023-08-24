package com.app.dto;

import java.time.LocalDate;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserBooking {
    LocalDate checkIn;
    LocalDate checkOut;
    String roomType;
}
