package com.example.hotel1.controller;

import com.example.hotel1.dto.HotelDto;
import com.example.hotel1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "/create/checkin", method = RequestMethod.GET)
    public void createCheckIn(@RequestBody HotelDto hotelDto) throws Exception{
        hotelService.createCheckIn(hotelDto);
        System.out.println((hotelDto.getHotelId()));
        System.out.println((hotelDto.getHotelCode()));
        System.out.println((hotelDto.getHotelRoomNum()));
        System.out.println((hotelDto.getHotelCheckIn()));
        System.out.println((hotelDto.getHotelCheckOut()));
    }

    @RequestMapping(value = "/update/checkin", method = RequestMethod.GET)
    public void updateCheckIn(@RequestBody HotelDto hotelDto) throws Exception{
        hotelService.updateCheckIn(hotelDto);
        System.out.println((hotelDto.getHotelId()));
        System.out.println((hotelDto.getHotelCode()));
        System.out.println((hotelDto.getHotelRoomNum()));
        System.out.println((hotelDto.getHotelCheckIn()));
        System.out.println((hotelDto.getHotelCheckOut()));
    }

    @RequestMapping(value = "/delete/checkin", method = RequestMethod.GET)
    public void deleteCheckIn(@RequestBody HotelDto hotelDto) throws Exception{
        hotelService.deleteCheckIn(hotelDto);
    }

    @RequestMapping(value = "/read/checkin/{hotelCode}", method = RequestMethod.GET)
    public List<HotelDto> selectCheckIn(@PathVariable("hotelCode") String hotelCode) throws Exception{
        return hotelService.selectCheckIn(hotelCode);
    }

}
