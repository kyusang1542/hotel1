package com.example.hotel1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@ApiModel(value = "HotelDto : 호텔 예약 정보", description = "호텔 예약 관련 정보가 담겨질 Dto")
@Data
public class HotelDto {
    @ApiModelProperty(value = "예약자 성함")
    private String hotelId;
    @ApiModelProperty(value = "호텔 분류 코드")
    private String hotelCode;
    @ApiModelProperty(value = "룸 분류 코드")
    private int hotelRoomCode;
    @ApiModelProperty(value = "체크인 시간")
    private Timestamp hotelCheckIn;
    @ApiModelProperty(value = "체크 아웃 시간")
    private Timestamp hotelCheckOut;

    private HotelNameDto hotelNameDto;
    private HotelRoomDto hotelRoomDto;
}
