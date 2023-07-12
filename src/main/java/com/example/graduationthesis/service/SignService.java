package com.example.graduationthesis.service;

import com.example.graduationthesis.data.dto.UserDto;
import com.example.graduationthesis.data.entity.User;

public interface SignService {
    String signUp(User user);

    UserDto signIn(String name, String pw);
}
