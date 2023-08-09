package com.example.graduationthesis.repository;

import com.example.graduationthesis.data.dto.UserResponseDto;
import com.example.graduationthesis.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface SignRepository extends JpaRepository<User, String> {
    User findByIdAndPw(String id, String pw);
    User getById(String id);
}
