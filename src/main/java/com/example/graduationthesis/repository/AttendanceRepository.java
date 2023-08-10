package com.example.graduationthesis.repository;

import com.example.graduationthesis.data.entity.Attendance;
import com.example.graduationthesis.data.entity.Lecture;
import com.example.graduationthesis.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByUser(User user);
    List<Attendance> findByLecture(Lecture lecture);
    List<Attendance> findByLectureAndUser(User user, Lecture lecture);


}
