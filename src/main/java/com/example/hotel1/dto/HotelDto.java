package com.example.hotel1.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class HotelDto {
    private String hotelId;
    private String hotelCode;
    private int hotelRoomCode;
    private Timestamp hotelCheckIn;
    private Timestamp hotelCheckOut;

    private HotelNameDto hotelNameDto;
    private HotelRoomDto hotelRoomDto;
}
