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
        if (signRepository.existsById(userDto.getId())) {
            throw new IllegalArgumentException("키 값 중복");

        }
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
    public UserResponseDto signIn(String id, String pw) {
        User user = signRepository.findByIdAndPw(id,pw);

        if (user != null) {

            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setName(user.getName());
            userResponseDto.setId(user.getId());
            userResponseDto.setRole(user.getRole());
            return userResponseDto;
        }
        return null;
    }
}
