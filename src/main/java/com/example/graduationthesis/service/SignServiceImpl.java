package com.example.graduationthesis.service;

import com.example.graduationthesis.data.dto.UserDto;
import com.example.graduationthesis.data.dto.UserResponseDto;
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
    public UserResponseDto signUp(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPw(userDto.getPw());
        user.setName(userDto.getName());
        user.setRole("student");

        signRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setRole(user.getRole());

        return userResponseDto;
    }

    @Override
    public UserDto signIn(String name, String pw) {
        return null;
    }
}
