package com.example.hotel1.service;

import com.example.hotel1.dto.HotelDto;
import com.example.hotel1.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    public List<HotelDto> selectHotelCheckIn(String hotelCode) {
        return hotelMapper.selectHotelCheckIn(hotelCode);
    }

    @Override
    public List<HotelDto> selectIdCheckIn(String hotelId) {
        return hotelMapper.selectIdCheckIn(hotelId);
    }

    @Override
    public int checkInOverlappingTest(HotelDto hotelDto) {
        System.out.println(hotelDto.getHotelCheckIn()+"TestIn");
        System.out.println(hotelDto.getHotelCheckOut()+"TestOUT");

        Timestamp CheckInTime = hotelDto.getHotelCheckIn();
        Timestamp CheckOutTime = hotelDto.getHotelCheckOut();

        long time = (CheckOutTime.getTime()-CheckInTime.getTime());
        long day = (((time/1000) / 60) / 60) / 24 ;    // 초,분,시간,일을 의미함
        //long day = (I/86400000);  // 일을 한 번에 구할 수 있음
        System.out.println(day + "박" + (day+1) +"일");
        /*
        long hour = (((I/1000) / 60) / 60);
        long minute = (I/1000) / 60;
        long second = (I/1000) ;
        System.out.println(day + "날" + hour +"시간"+ minute + "분" + second + "초");
        */

        // 1박째 연산
        Timestamp t1 = CheckInTime;
        Timestamp t2 = CheckOutTime;
        int oneDay = 24*60*60*1000;
        t2 = new Timestamp(t1.getTime() + oneDay);
        hotelDto.setHotelCheckOut(t2);
        List<HotelDto> list = hotelMapper.checkInOverlappingOneDay(hotelDto);
        System.out.println("1번째 "+t1+" checkInTime, "+t2+" checkOutTime");
        System.out.println("1번째"+list.size());
        if (list.size()!=0) {
            System.out.println("checkIn이 불가능한 날짜입니다.");
            return 0;
        }

        // 2박째 ~ 마지막 연산
        for(int i=2; i<=day; i++){
            t1 = new Timestamp(t1.getTime() + oneDay);
            hotelDto.setHotelCheckIn(t1);
            t2 = new Timestamp(t2.getTime() + oneDay);
            hotelDto.setHotelCheckOut(t2);
            list = hotelMapper.checkInOverlappingOneDay(hotelDto);
            System.out.println(i+"번째 "+t1+" checkInTime, "+t2+" checkOutTime");
            System.out.println(i+"번째"+list.size());
            if (list.size()!=0) {
                System.out.println("checkIn이 불가능한 날짜입니다.");
                return 0;
            }
        }

        hotelDto.setHotelCheckIn(CheckInTime);
        hotelDto.setHotelCheckOut(CheckOutTime);
        return 1;
    }

    @Override
    public String checkId(HotelDto hotelDto) {

        return hotelMapper.checkId(hotelDto);
    }
}
