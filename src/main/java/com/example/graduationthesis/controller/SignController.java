package com.example.graduationthesis.controller;

import com.example.graduationthesis.data.dto.UserDto;
import com.example.graduationthesis.data.dto.UserResponseDto;
import com.example.graduationthesis.data.entity.User;
import com.example.graduationthesis.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignController {

    private final SignService signService;
    @Autowired
    public SignController(SignService signService){
        this.signService = signService;
    }

    @PostMapping("/saveData")
    public String saveData(@RequestParam String name, @RequestParam String id, @RequestParam String pw) {
        UserDto userDto = new UserDto();

        userDto.setId(id);
        userDto.setPw(pw);
        userDto.setName(name);
        userDto.setRole("student");

        UserResponseDto userResponseDto = signService.signUp(userDto);

        return "유저 데이터 저장됨.";

    }
}
