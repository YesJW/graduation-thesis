package com.example.graduationthesis.data.dto;

import com.example.graduationthesis.data.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LectureDto {
    private Long id;
    private String lectureCode;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private User professor;
}
