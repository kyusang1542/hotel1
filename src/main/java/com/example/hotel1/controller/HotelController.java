package com.example.hotel1.controller;

import com.example.hotel1.dto.HotelDto;
import com.example.hotel1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // 체크인, 체크아웃 날짜 중복 체크 후 예약
    @RequestMapping(value = "/create/checkin", method = RequestMethod.POST)
    public String createCheckInOverlapping(HttpServletRequest request) throws Exception{
        HotelDto hotelDto = new HotelDto();

        hotelDto.setHotelId(request.getParameter("hotelId"));
        hotelDto.setHotelCode(request.getParameter("hotelCode"));
        hotelDto.setHotelRoomCode(Integer.parseInt(request.getParameter("hotelRoomCode")));
        //hotelDto.setHotelCheckIn(Timestamp.valueOf("2022-10-01 00:00:00"));
        //hotelDto.setHotelCheckOut(Timestamp.valueOf("2022-10-03 00:00:00"));
        hotelDto.setHotelCheckIn(Timestamp.valueOf(request.getParameter("hotelCheckIn") + " 00:00:00"));
        hotelDto.setHotelCheckOut(Timestamp.valueOf(request.getParameter("hotelCheckOut") + " 00:00:00"));

        int Overlapping = hotelService.checkInOverlappingTest(hotelDto);

        // Overlapping == 0 체크인 중복 있음 , 1 중복 없음
        if(Overlapping == 0) {
            System.out.println("checkIn이 불가능한 날짜입니다.");
        } else if (Overlapping == 1) {
            System.out.println("checkIn이 가능한 날짜입니다.");
            hotelService.createCheckIn(hotelDto);
        }

        return "/hotel/checkIn";
    }

    @RequestMapping("/hotel/checkIn")
    public String openCheckIn() throws Exception{
        return "/hotel/checkIn";
    }

    @RequestMapping("/hotel/index")
    public String openIndex() throws Exception{
        return "/hotel/index";
    }

}
