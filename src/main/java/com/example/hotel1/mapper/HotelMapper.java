package com.example.hotel1.mapper;

import com.example.hotel1.dto.HotelDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelMapper {
    void createCheckIn(HotelDto hotelDto);

    void updateCheckIn(HotelDto hotelDto);

    void deleteCheckIn(HotelDto hotelDto);

    List<HotelDto> selectHotelCheckIn(String hotelCode);

    List<HotelDto> checkInOverlapping(HotelDto hotelDto);

    List<HotelDto> selectIdCheckIn(String hotelId);

    List<HotelDto> checkInOverlappingOneDay(HotelDto hotelDto);

    String checkId(HotelDto hotelDto);
}
