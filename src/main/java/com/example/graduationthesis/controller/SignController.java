package com.example.graduationthesis.controller;

import com.example.graduationthesis.data.entity.User;
import com.example.graduationthesis.repository.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignController {

    private final SignRepository signRepository;

    @Autowired
    public SignController(SignRepository signRepository) {
        this.signRepository = signRepository;
    }

    @PostMapping("/saveData")
    public String saveData(@RequestParam String name, @RequestParam String id, @RequestParam String pw) {
        User user = new User(id, pw, name, "0");
        signRepository.save(user);
        return "유저 데이터 저장됨.";

    }
}
