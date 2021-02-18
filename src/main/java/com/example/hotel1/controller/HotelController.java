package com.example.hotel1.controller;

import com.example.hotel1.dto.HotelDto;
import com.example.hotel1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // 호텔별 예약 확인
    @RequestMapping(value = "/read/hotel/checkin", method = RequestMethod.GET)
    public ModelAndView selectHotelCheckIn(HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("/hotel/reservationStatusResult");

        List<HotelDto> hotel = hotelService.selectHotelCheckIn(request.getParameter("hotelCode"));

        mv.addObject("hotelName", request.getParameter("hotelCode"));
        mv.addObject("hotel", hotel);

        return mv;
    }

    // 체크인, 체크아웃 날짜 중복 체크 후 예약 변경
    @RequestMapping(value = "/update/checkin", method = RequestMethod.POST)
    public ModelAndView updateCheckIn(HttpServletRequest request) throws Exception{
        HotelDto hotelDto = new HotelDto();

        hotelDto.setHotelId(request.getParameter("hotelId"));
        hotelDto.setHotelCode(request.getParameter("hotelCode"));
        hotelDto.setHotelRoomCode(Integer.parseInt(request.getParameter("hotelRoomCode")));
        hotelDto.setHotelCheckIn(Timestamp.valueOf(request.getParameter("hotelCheckIn") + " 00:00:00"));
        hotelDto.setHotelCheckOut(Timestamp.valueOf(request.getParameter("hotelCheckOut") + " 00:00:00"));

        int Overlapping = hotelService.checkInOverlappingTest(hotelDto);

        // Overlapping == 0 체크인 중복 있음 , 1 중복 없음
        if(Overlapping == 0) {
            System.out.println("checkIn이 불가능한 날짜입니다.");
        } else if (Overlapping == 1) {
            System.out.println("checkIn이 가능한 날짜입니다.");
            hotelService.updateCheckIn(hotelDto);
        }

        ModelAndView mv = new ModelAndView("/hotel/reservationChangeResult");
        List<HotelDto> hotel = hotelService.selectIdCheckIn(hotelDto.getHotelId());
        System.out.println(hotel);
        mv.addObject("hotel", hotel);

        return mv;
    }

    // 호텔 개인 예매 정보 확인
    @RequestMapping(value = "/read/id/checkin", method = RequestMethod.POST)
    public ModelAndView selectIdCheckIn(HttpServletRequest request) throws Exception{
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelId(request.getParameter("hotelId"));

        ModelAndView mv = new ModelAndView("/hotel/reservationConfirmationResult");
        List<HotelDto> hotel = hotelService.selectIdCheckIn(hotelDto.getHotelId());
        System.out.println(hotel);
        mv.addObject("hotel", hotel);


        return mv;
    }

    // 예약 취소
    @RequestMapping(value = "/delete/checkin", method = RequestMethod.POST)
    public String deleteCheckIn(HttpServletRequest request) throws Exception{
        HotelDto hotelDto = new HotelDto();

        hotelDto.setHotelId(request.getParameter("hotelId"));

        hotelService.deleteCheckIn(hotelDto);

        return "/hotel/cancellation";
    }

    // 체크인, 체크아웃 날짜 중복 체크 후 예약
    @RequestMapping(value = "/create/checkin", method = RequestMethod.POST)
    public ModelAndView createCheckInOverlapping(HttpServletRequest request) throws Exception{
        HotelDto hotelDto = new HotelDto();

        hotelDto.setHotelId(request.getParameter("hotelId"));
        hotelDto.setHotelCode(request.getParameter("hotelCode"));
        hotelDto.setHotelRoomCode(Integer.parseInt(request.getParameter("hotelRoomCode")));
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

        ModelAndView mv = new ModelAndView("/hotel/checkInResult");
        List<HotelDto> hotel = hotelService.selectIdCheckIn(hotelDto.getHotelId());
        System.out.println(hotel);
        mv.addObject("hotel", hotel);

        return mv;
    }

    // 호텔별 예약 현황 페이지 이동
    @RequestMapping("/hotel/reservation/status")
    public String openReservationStatus() throws Exception{
        return "/hotel/reservationStatus";
    }

    // 예약 변경 페이지 이동
    @RequestMapping("/hotel/reservation/change")
    public String openReservationChange() throws Exception{
        return "/hotel/reservationChange";
    }

    // 개인 예약 정보 페이지 이동
    @RequestMapping("/hotel/reservation/confirmation")
    public String openReservationConfirmation() throws Exception{
        return "/hotel/reservationConfirmation";
    }

    // 예약 취소 페이지 이동
    @RequestMapping("/hotel/reservation/cancellation")
    public String openCancellation() throws Exception{
        return "/hotel/cancellation";
    }

    // 호텔 예약 페이지 이동
    @RequestMapping("/hotel/reservation/checkin")
    public String openCheckIn() throws Exception{
        return "/hotel/checkIn";
    }

    // 시스템 안내 페이지 이동
    @RequestMapping("/hotel/index")
    public String openIndex() throws Exception{
        return "/hotel/index";
    }

}
