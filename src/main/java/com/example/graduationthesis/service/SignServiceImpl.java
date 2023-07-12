package com.example.graduationthesis.service;

import com.example.graduationthesis.data.dto.UserDto;
import com.example.graduationthesis.data.entity.User;
import com.example.graduationthesis.repository.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService{
    private SignRepository signRepository;

    @Autowired
    public SignServiceImpl(SignRepository signRepository) {
        this.signRepository = signRepository;}

    @Override
    public String signUp(User user) {
        return null;
    }

    @Override
    public UserDto signIn(String name, String pw) {
        return null;
    }
}
