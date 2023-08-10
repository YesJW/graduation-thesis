package com.example.graduationthesis.data.dto;

import com.example.graduationthesis.data.entity.Lecture;
import com.example.graduationthesis.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class AttendanceDto {
    private User user;
    private Lecture lecture;
    private Date date;
}
