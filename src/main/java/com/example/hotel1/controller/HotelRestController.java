package com.example.hotel1.controller;

import com.example.hotel1.dto.HotelDto;
import com.example.hotel1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelRestController {

    @Autowired
    private HotelService hotelService;

    /*
    @RequestMapping(value = "/create/checkin", method = RequestMethod.GET)
    public void createCheckIn(@RequestBody HotelDto hotelDto) throws Exception{
        hotelService.createCheckIn(hotelDto);
        System.out.println((hotelDto.getHotelId()));
        System.out.println((hotelDto.getHotelCode()));
        System.out.println((hotelDto.getHotelRoomNum()));
        System.out.println((hotelDto.getHotelCheckIn()));
        System.out.println((hotelDto.getHotelCheckOut()));
    }
    */

    /*
    // 체크인, 체크아웃 날짜 중복 체크 후 예약
    @RequestMapping(value = "/rest/create/checkin", method = RequestMethod.GET)
    public void createCheckInOverlapping(@RequestBody HotelDto hotelDto) throws Exception{
        List<HotelDto> list = hotelService.checkInOverlapping(hotelDto);
        System.out.println(list.size());
        if(list.size() == 0) {
            System.out.println("checkIn이 가능한 날짜입니다.");
            hotelService.createCheckIn(hotelDto);
        } else {
            System.out.println("checkIn이 불가능한 날짜입니다.");
        }
        System.out.println((hotelDto.getHotelId()));
        System.out.println((hotelDto.getHotelCode()));
        System.out.println((hotelDto.getHotelRoomCode()));
        System.out.println((hotelDto.getHotelCheckIn()));
        System.out.println((hotelDto.getHotelCheckOut()));
    }
    */

    // 체크인, 체크아웃 날짜 중복 체크 후 예약 변경
    @RequestMapping(value = "/rest/update/checkin", method = RequestMethod.GET)
    public void updateCheckIn(@RequestBody HotelDto hotelDto) throws Exception{
        List<HotelDto> list = hotelService.checkInOverlapping(hotelDto);
        System.out.println(list.size());
        System.out.println(list);
        if(list.size() == 0) {
            System.out.println("checkIn이 가능한 날짜입니다.");
            hotelService.updateCheckIn(hotelDto);
        } else {
            System.out.println("checkIn이 불가능한 날짜입니다.");
        }
        System.out.println((hotelDto.getHotelId()));
        System.out.println((hotelDto.getHotelCode()));
        System.out.println((hotelDto.getHotelRoomCode()));
        System.out.println((hotelDto.getHotelCheckIn()));
        System.out.println((hotelDto.getHotelCheckOut()));
    }

    // 예약 취소
    @RequestMapping(value = "/rest/delete/checkin", method = RequestMethod.GET)
    public void deleteCheckIn(@RequestBody HotelDto hotelDto) throws Exception{
        hotelService.deleteCheckIn(hotelDto);
    }

    // 호텔별 예약 확인
    @RequestMapping(value = "/rest/read/hotel/checkin/{hotelCode}", method = RequestMethod.GET)
    public List<HotelDto> selectHotelCheckIn(@PathVariable("hotelCode") String hotelCode) throws Exception{
        return hotelService.selectHotelCheckIn(hotelCode);
    }

    // 호텔 개인 예매 정보 확인
    @RequestMapping(value = "/rest/read/id/checkin/{hotelId}", method = RequestMethod.GET)
    public List<HotelDto> selectIdCheckIn(@PathVariable("hotelId") String hotelId) throws Exception{
        return hotelService.selectIdCheckIn(hotelId);
    }

    // 체크인, 체크아웃 날짜 중복 체크 후 예약 수정버전
    @RequestMapping(value = "/rest/create/checkin/test", method = RequestMethod.GET)
    public void createCheckInOverlappingTest(@RequestBody HotelDto hotelDto) throws Exception{
        int Overlapping = hotelService.checkInOverlappingTest(hotelDto);

        // Overlapping == 0 체크인 중복 있음 , 1 중복 없음
        if(Overlapping == 0) {
            System.out.println("checkIn이 불가능한 날짜입니다.");
        } else if (Overlapping == 1) {
            System.out.println("checkIn이 가능한 날짜입니다.");
            hotelService.createCheckIn(hotelDto);
        }

    }

}
