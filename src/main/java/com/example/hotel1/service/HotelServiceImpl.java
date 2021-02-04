package com.example.hotel1.service;

import com.example.hotel1.dto.HotelDto;
import com.example.hotel1.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelMapper hotelMapper;


    @Override
    public void createCheckIn(HotelDto hotelDto) {
        hotelMapper.createCheckIn(hotelDto);
    }

    @Override
    public void updateCheckIn(HotelDto hotelDto) {
        hotelMapper.updateCheckIn(hotelDto);
    }

    @Override
    public void deleteCheckIn(HotelDto hotelDto) {
        hotelMapper.deleteCheckIn(hotelDto);
    }

    @Override
    public List<HotelDto> selectCheckIn(String hotelCode) {
        return hotelMapper.selectCheckIn(hotelCode);
    }

}
