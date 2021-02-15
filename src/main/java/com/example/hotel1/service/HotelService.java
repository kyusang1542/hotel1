package com.example.hotel1.service;

import com.example.hotel1.dto.HotelDto;

import java.util.List;

public interface HotelService {

    void createCheckIn(HotelDto hotelDto);

    void updateCheckIn(HotelDto hotelDto);

    void deleteCheckIn(HotelDto hotelDto);

    List<HotelDto> selectHotelCheckIn(String hotelCode);

    List<HotelDto> checkInOverlapping(HotelDto hotelDto);

    List<HotelDto> selectIdCheckIn(String hotelId);

    int checkInOverlappingTest(HotelDto hotelDto);

}
