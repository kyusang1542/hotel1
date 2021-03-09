package com.example.hotel1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "hotelNameDto : 룸 정보", description = "룸 정보가 담겨질 Dto")
@Data
public class HotelRoomDto {
    @ApiModelProperty(value = "룸 분류 코드")
    private int hotelRoomCode;
    @ApiModelProperty(value = "룸 종류")
    private String hotelGrade;
    @ApiModelProperty(value = "룸 가격")
    private int hotelPrice;
}
