package com.example.graduationthesis.repository;

import com.example.graduationthesis.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignRepository extends JpaRepository<User, String> {
}
