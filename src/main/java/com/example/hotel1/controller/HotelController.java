package com.example.hotel1.controller;

import com.example.hotel1.dto.HotelDto;
import com.example.hotel1.service.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@Api(description = "호텔 Controller API")
@Controller
public class HotelController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HotelService hotelService;

    // 호텔별 예약 확인
    @ApiOperation("호텔별 예약 확인")
    @RequestMapping(value = "/read/hotel/checkin", method = RequestMethod.GET)
    public ModelAndView selectHotelCheckIn(HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("/hotel/reservationStatusResult");

        //log.debug("로그 출력 테스트");

        List<HotelDto> hotel = hotelService.selectHotelCheckIn(request.getParameter("hotelCode"));

        mv.addObject("hotelName", request.getParameter("hotelCode"));
        mv.addObject("hotel", hotel);

        return mv;
    }

    // 체크인, 체크아웃 날짜 중복 체크 후 예약 변경
    @ApiOperation("예약 변경")
    @RequestMapping(value = "/update/checkin", method = RequestMethod.POST)
    public ModelAndView updateCheckIn(HttpServletRequest request) throws Exception{
        HotelDto hotelDto = new HotelDto();

        hotelDto.setHotelId(request.getParameter("hotelId"));
        hotelDto.setHotelCode(request.getParameter("hotelCode"));
        hotelDto.setHotelRoomCode(Integer.parseInt(request.getParameter("hotelRoomCode")));
        hotelDto.setHotelCheckIn(Timestamp.valueOf(request.getParameter("hotelCheckIn") + " 00:00:00"));
        hotelDto.setHotelCheckOut(Timestamp.valueOf(request.getParameter("hotelCheckOut") + " 00:00:00"));

        int Overlapping = hotelService.checkInOverlapping(hotelDto);

        ModelAndView mv;

        // Overlapping == 0 체크인 중복 있음 , 1 중복 없음
        if(Overlapping == 0) {
            System.out.println("checkIn이 불가능한 날짜입니다.");
            mv = new ModelAndView("/hotel/reservationChangeResultFail");
        } else if (Overlapping == 1) {
            System.out.println("checkIn이 가능한 날짜입니다.");

            List<HotelDto> hotel = hotelService.selectIdCheckIn(hotelDto.getHotelId());
            System.out.println(hotel);

            // 호텔에 예약이 되어있는지 조회
            if(hotel.size()==0){
                mv = new ModelAndView("/hotel/reservationChangeIdResultFail");
            } else {
                mv = new ModelAndView("/hotel/reservationChangeResult");
            }

            mv.addObject("hotel", hotel);

            hotelService.updateCheckIn(hotelDto);
        } else {
            // CheckInTime이 CheckOutTime과 같거나 큼.
            System.out.println("CheckInTime이 CheckOutTime과 같거나 큽니다.");
            mv = new ModelAndView("/hotel/reservationChangeTimeResultFail");
        }

        return mv;
    }

    // 호텔 개인 예약 정보 확인
    @ApiOperation(value = "개인 예약 확인")
    @RequestMapping(value = "/read/id/checkin", method = RequestMethod.POST)
    public ModelAndView selectIdCheckIn(HttpServletRequest request) throws Exception{
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelId(request.getParameter("hotelId"));

        List<HotelDto> hotel = hotelService.selectIdCheckIn(hotelDto.getHotelId());

        ModelAndView mv;

        if(hotel.size()==0){
            // 개인 예약 정보 조회 결과가 없음
            mv = new ModelAndView("/hotel/reservationConfirmationResultFail");
        } else {
            // 개인 예약 정보 조회 결과 있음
            mv = new ModelAndView("/hotel/reservationConfirmationResult");
        }

        System.out.println(hotel);
        mv.addObject("hotel", hotel);

        return mv;
    }

    // 예약 취소
    @ApiOperation("예약 취소")
    @RequestMapping(value = "/delete/checkin", method = RequestMethod.POST)
    public ModelAndView deleteCheckIn(HttpServletRequest request) throws Exception{
        HotelDto hotelDto = new HotelDto();

        hotelDto.setHotelId(request.getParameter("hotelId"));

        String id = hotelService.checkId(hotelDto);
        System.out.println(id);

        ModelAndView mv;
        if(id==null){
            // 개인 예약 정보 조회 결과가 없음
            mv = new ModelAndView("/hotel/cancellationResultFail");
        }else {
            // 개인 예약 정보 조회 결과 있음
            mv = new ModelAndView("/hotel/cancellationResult");
            hotelService.deleteCheckIn(hotelDto);
        }

        //에러 강제 발생
        //int i = 10/0;

        return mv;
    }

    // 체크인, 체크아웃 날짜 중복 체크 후 예약
    @ApiOperation(value = "체크인(예약)")
    @RequestMapping(value = "/create/checkin", method = RequestMethod.POST)
    public ModelAndView createCheckInOverlapping(HttpServletRequest request) throws Exception{
        HotelDto hotelDto = new HotelDto();

        hotelDto.setHotelId(request.getParameter("hotelId"));
        hotelDto.setHotelCode(request.getParameter("hotelCode"));
        hotelDto.setHotelRoomCode(Integer.parseInt(request.getParameter("hotelRoomCode")));
        hotelDto.setHotelCheckIn(Timestamp.valueOf(request.getParameter("hotelCheckIn") + " 00:00:00"));
        hotelDto.setHotelCheckOut(Timestamp.valueOf(request.getParameter("hotelCheckOut") + " 00:00:00"));

        ModelAndView mv;

        String id = hotelService.checkId(hotelDto);
        System.out.println(id);

        if(id != null) {
            // id != null 이미 예약한 사람이 중복 예약시도
            System.out.println("이미 예약한 사람입니다.");
            mv = new ModelAndView("/hotel/checkInIdResultFail");
            return mv;
        }

        int Overlapping = hotelService.checkInOverlapping(hotelDto);

        if (Overlapping == 1) {
            // 체크인 가능
            System.out.println("checkIn이 가능한 날짜입니다.");
            hotelService.createCheckIn(hotelDto);

            mv = new ModelAndView("/hotel/checkInResult");
            List<HotelDto> hotel = hotelService.selectIdCheckIn(hotelDto.getHotelId());

            System.out.println(hotel);
            mv.addObject("hotel", hotel);

        } else if (Overlapping == 0) {
            // 이미 예약된 룸에 체크인 시도
            System.out.println("checkIn이 불가능한 날짜입니다.");
            mv = new ModelAndView("/hotel/checkInResultFail");
        } else {
            // CheckInTime이 CheckOutTime과 같거나 큼.
            System.out.println("CheckInTime이 CheckOutTime과 같거나 큽니다.");
            mv = new ModelAndView("/hotel/checkInTimeResultFail");
        }

        return mv;
    }

    // 호텔별 예약 현황 페이지 이동
    @ApiOperation(value = "호텔별 예약 현황 페이지 이동")
    @RequestMapping(value = "/hotel/reservation/status", method = RequestMethod.GET)
    public String openReservationStatus() throws Exception{
        return "/hotel/reservationStatus";
    }

    // 예약 변경 페이지 이동
    @ApiOperation(value = "예약 변경 페이지 이동")
    @RequestMapping(value = "/hotel/reservation/change", method = RequestMethod.GET)
    public String openReservationChange() throws Exception{
        return "/hotel/reservationChange";
    }

    // 개인 예약 정보 페이지 이동
    @ApiOperation(value = "개인 예약 정보 페이지 이동")
    @RequestMapping(value = "/hotel/reservation/confirmation", method = RequestMethod.GET)
    public String openReservationConfirmation() throws Exception{
        return "/hotel/reservationConfirmation";
    }

    // 예약 취소 페이지 이동
    @ApiOperation(value = "예약 취소 페이지 이동")
    @RequestMapping(value = "/hotel/reservation/cancellation", method = RequestMethod.GET)
    public String openCancellation() throws Exception{
        return "/hotel/cancellation";
    }

    // 호텔 예약 페이지 이동
    @ApiOperation(value = "호텔 예약 페이지 이동")
    @RequestMapping(value = "/hotel/reservation/checkin", method = RequestMethod.GET)
    public String openCheckIn() throws Exception{
        return "/hotel/checkIn";
    }

    // 시스템 안내 페이지 이동
    @ApiOperation(value = "시스템 안내 페이지 이동")
    @RequestMapping(value = "/hotel/index", method = RequestMethod.GET)
    public String openIndex() throws Exception{
        return "/hotel/index";
    }

}
