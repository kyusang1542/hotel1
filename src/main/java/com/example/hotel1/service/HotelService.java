package com.example.hotel1.service;

import com.example.hotel1.dto.HotelDto;

import java.util.List;

public interface HotelService {

    void createCheckIn(HotelDto hotelDto);

    void updateCheckIn(HotelDto hotelDto);

    void deleteCheckIn(HotelDto hotelDto);

    List<HotelDto> selectHotelCheckIn(String hotelCode);

    List<HotelDto> selectIdCheckIn(String hotelId);

    int checkInOverlappingTest(HotelDto hotelDto);

    // 예약자 이름을 비교하여 실제로 예약이 되어 있다면 예약자 이름을 반환
    String checkId(HotelDto hotelDto);
}
