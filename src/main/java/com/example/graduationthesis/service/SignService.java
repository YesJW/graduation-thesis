package com.example.graduationthesis.service;

import com.example.graduationthesis.data.dto.UserDto;
import com.example.graduationthesis.data.dto.UserResponseDto;

public interface SignService {
    UserResponseDto signUp(UserDto userDto);

    UserResponseDto signIn(String id, String pw);
}
