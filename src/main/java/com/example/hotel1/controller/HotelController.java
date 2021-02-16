package com.example.hotel1.controller;

import com.example.hotel1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping("/index")
    public String openIndex() throws Exception{

        return "/hotel/index";
    }

}
