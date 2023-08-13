package com.example.graduationthesis.controller;

import com.example.graduationthesis.data.dto.ImgDto;
import com.example.graduationthesis.data.dto.UserDto;
import com.example.graduationthesis.data.dto.UserResponseDto;
import com.example.graduationthesis.data.entity.User;
import com.example.graduationthesis.service.AttendanceService;
import com.example.graduationthesis.service.SignService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;
    private final AttendanceService attendanceService;

    @Value("${spring.servlet.multipart.location}")
    private String absolutePath;

    @PostMapping("/signUp")
    public String saveData(@RequestParam String name, @RequestParam String id, @RequestParam String pw) {
        UserDto userDto = new UserDto();

        userDto.setId(id);
        userDto.setPw(pw);
        userDto.setName(name);
        userDto.setRole("student");

        UserResponseDto userResponseDto = signService.signUp(userDto);

        return "유저 데이터 저장됨.";

    }

    @PostMapping("/add_face")
    public String addFace(
            @RequestPart(value = "studentNum") String studentNum,
            @RequestPart(value = "faceImage") List<MultipartFile> faceImages) throws IOException, ParseException {
        // TODO: 2023/08/12 유저에게 얼굴 사진 리스트를 받고 학습 서버로 전송
        // TODO: 2023/08/12 학습이 결과에 따른 반환값 리턴

        for (MultipartFile mFile : faceImages) {
            ImgDto imgDto = new ImgDto(
                    UUID.randomUUID().toString(),
                    mFile.getOriginalFilename()
            );
            File dirFile = new File(absolutePath+"/res/student_face_data/" + studentNum);
            boolean mkdirResult = dirFile.mkdir();
            log.info("mkdirResult = " + mkdirResult);

            File saveFile = new File("res/student_face_data/"+studentNum+"/"+imgDto.getUuid() + "_" + imgDto.getFileName());
            mFile.transferTo(saveFile);
        }

        JSONObject responseJson = attendanceService.sendDataForValidation("add_student", studentNum, absolutePath+"/res/student_face_image/"+studentNum);

        return responseJson.toJSONString();
    }

    @PostMapping("/signIn")
    public String loadData(@RequestParam String id, @RequestParam String pw) {
        UserResponseDto userResponseDto = signService.signIn(id, pw);
        if (userResponseDto == null && userResponseDto.getRole().equals("student")) {
            return "0";
        }
        else
            return "1";

    }

    @GetMapping("/login_web")
    public ResponseEntity<UserResponseDto> login_web(@RequestParam String id, @RequestParam String pw, HttpSession session){

        UserResponseDto userResponseDto = signService.signIn(id, pw);

        if (userResponseDto != null && userResponseDto.getRole().equals("professor")) {
            session.setAttribute("user", userResponseDto);
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
        }
        else {
            return null;
        }

    }

}
