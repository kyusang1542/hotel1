package com.example.hotel1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "hotelNameDto : 호텔 정보", description = "호텔 정보가 담겨질 Dto")
@Data
public class HotelNameDto {
    @ApiModelProperty(value = "호텔 분류 코드")
    private String hotelCode;
    @ApiModelProperty(value = "호텔 전화 번호")
    private String hotelTel;
    @ApiModelProperty(value = "호텔 주소")
    private String hotelAdd;
    @ApiModelProperty(value = "호텔 이름")
    private String hotelName;
}
