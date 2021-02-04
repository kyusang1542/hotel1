package com.example.hotel1.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class HotelDto {
    private String hotelId;
    private String hotelCode;
    private int hotelRoomNum;
    private Timestamp hotelCheckIn;
    private Timestamp hotelCheckOut;
}
