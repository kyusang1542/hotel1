package com.example.hotel1.dto;

import lombok.Data;

@Data
public class HotelQnADto {
    private int qnaIdx;
    private char answerYN;
    private String userName;
    private String title;
    private String contents;
    private String answer;
}
