package com.example.graduationthesis.service;

import com.example.graduationthesis.data.dto.LectureDto;

import java.util.List;

public interface LectureService {

    List<LectureDto> getLectureList(String id);

    List<String> getStudentLectureList(String id);

    String getLectureTitle(String lectureCode, String id);

}
