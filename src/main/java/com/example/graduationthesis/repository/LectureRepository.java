package com.example.graduationthesis.repository;

import com.example.graduationthesis.data.dto.LectureDto;
import com.example.graduationthesis.data.entity.Lecture;
import com.example.graduationthesis.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByUser(User user);

    Lecture findByLectureCode(String code);

    Lecture findByLectureCodeAndUser_Id(String code, String id);



}
