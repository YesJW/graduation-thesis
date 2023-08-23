package com.example.graduationthesis.controller;

import com.example.graduationthesis.data.dto.LectureDto;
import com.example.graduationthesis.data.dto.UserResponseDto;
import com.example.graduationthesis.service.LectureService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MainPageController {

    private static Logger LOGGER = LoggerFactory.getLogger(MainPageController.class);
    private  LectureService lectureService;

    public MainPageController(LectureService lectureService){
        this.lectureService = lectureService;}

    @GetMapping("/mainPage")
    public ModelAndView mainPage(){
        return new ModelAndView("mainPage");
    }
    @GetMapping("/getLecture")
    public ResponseEntity<List<LectureDto>> getLecture(HttpSession session) {
        // 세션에서 로그인 정보 확인
        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute("user");
        List<LectureDto> lectureDtos = lectureService.getLectureList(userResponseDto.getId());
        LOGGER.info("[mainPage id check]" + userResponseDto.getId());
        if (userResponseDto != null && userResponseDto.getRole().equals("professor")) {
            LOGGER.info("[mainPageController]" + lectureDtos);

            // 교수 역할일 때 필요한 작업 수행
            return ResponseEntity.status(HttpStatus.OK).body(lectureDtos);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
