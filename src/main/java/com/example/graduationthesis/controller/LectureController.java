package com.example.graduationthesis.controller;

import com.example.graduationthesis.service.LectureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LectureController {

    private LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(LectureController.class);


    @PostMapping("/qrCheck")
    public List<String> check_Lecture(@RequestParam String id) {
        LOGGER.info("qr체크 강의 목록 확인 ");
        List<String> lectureList = lectureService.getStudentLectureList(id);
        return lectureList;

    }

    @PostMapping("/getLectureTitle")
    public String getLectureTitle(@RequestParam String lectureCode, @RequestParam String id) {
        String lectureTitle = lectureService.getLectureTitle(lectureCode, id);
        return lectureTitle;
    }
}
