package com.example.graduationthesis.controller;

import com.example.graduationthesis.data.dto.UserDto;
import com.example.graduationthesis.data.dto.UserResponseDto;
import com.example.graduationthesis.data.entity.User;
import com.example.graduationthesis.service.SignService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignController {

    private final SignService signService;

    @Autowired
    public SignController(SignService signService) {
        this.signService = signService;
    }

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
    public String addFace() {
        // TODO: 2023/08/12 유저에게 얼굴 사진 리스트를 받고 학습 서버로 전송
        // TODO: 2023/08/12 학습이 결과에 따른 반환값 리턴

        return "";
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
