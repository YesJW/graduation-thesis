package com.example.graduationthesis.service;

import com.example.graduationthesis.data.dto.LectureDto;
import com.example.graduationthesis.data.entity.Lecture;
import com.example.graduationthesis.data.entity.User;
import com.example.graduationthesis.repository.LectureRepository;
import com.example.graduationthesis.repository.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LectureServiceImpl implements LectureService{

    private LectureRepository lectureRepository;
    private SignRepository signRepository;

    @Autowired
    public LectureServiceImpl(LectureRepository lectureRepository, SignRepository signRepository){
        this.lectureRepository = lectureRepository;
        this.signRepository = signRepository;
    }
    @Override
    public List<LectureDto> getLectureList(String id) {
        User user = signRepository.getById(id);
        System.out.println(user);
        List<Lecture> lectures = lectureRepository.findByUser(user);
        List<LectureDto> lectureDtos = new ArrayList<>();
        for (Lecture l : lectures){
            LectureDto lectureDto = new LectureDto();
            lectureDto.setLectureCode(l.getLectureCode());
            lectureDto.setId(l.getId());
            lectureDto.setTitle(l.getTitle());
            lectureDto.setStartDate(l.getStartDate());
            lectureDto.setEndDate(l.getEndDate());
            lectureDtos.add(lectureDto);
        }
        return lectureDtos;
    }

    @Override
    public List<String> getStudentLectureList(String id) {
        User user = signRepository.getById(id);
        List<Lecture> lectures = lectureRepository.findByUser(user);
        List<String> lectureList = new ArrayList<>();
        for (Lecture l : lectures){
            String lecture = l.getLectureCode();
            lectureList.add(lecture);
        }

        System.out.println(lectureList);

        return lectureList;
    }

    @Override
    public String getLectureTitle(String lectureCode, String id) {
        Lecture lecture = lectureRepository.findByLectureCodeAndUser_Id(lectureCode,id);
        System.out.println(lecture.getTitle());

        String lectureTitle = lecture.getTitle();

        return lectureTitle;
    }
}
