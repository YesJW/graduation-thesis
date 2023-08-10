package com.example.graduationthesis.service;

import com.example.graduationthesis.data.dto.AttendanceDto;
import com.example.graduationthesis.data.entity.Attendance;
import com.example.graduationthesis.data.entity.Lecture;
import com.example.graduationthesis.data.entity.User;
import com.example.graduationthesis.repository.AttendanceRepository;
import com.example.graduationthesis.repository.LectureRepository;
import com.example.graduationthesis.repository.SignRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceService {
    private AttendanceRepository attendanceRepository;
    private SignRepository signRepository;
    private LectureRepository lectureRepository;


    public List<Attendance> getAttendanceByUser(String userId) {
        User user = signRepository.getById(userId);
        return attendanceRepository.findByUser(user);
    }
    public List<Attendance> getAttendanceByLecture(String lectureCode) {
        Lecture lecture = lectureRepository.findByLectureCode(lectureCode);
        return attendanceRepository.findByLecture(lecture);
    }
    public List<Attendance> getAttendanceByLectureUser(String lectureCode, String userId) {
        Lecture lecture = lectureRepository.findByLectureCode(lectureCode);
        User user = signRepository.getById(userId);
        return attendanceRepository.findByLectureAndUser(lecture, user);
    }
    public List<Attendance> getAttendanceByLectureUserDate(String lectureCode, String userId, Date date) {
        Lecture lecture = lectureRepository.findByLectureCode(lectureCode);
        User user = signRepository.getById(userId);
        return attendanceRepository.findByLectureAndUserAndDate(lecture, user, date);
    }

    public Attendance saveAttendance(String userId, String lectureCode) {
        User user = signRepository.getById(userId);
        Lecture lecture = lectureRepository.findByLectureCode(lectureCode);
        LocalDate now = LocalDate.now();
        Attendance newAttendance = Attendance.builder().user(user).lecture(lecture).date(now).build();

        return attendanceRepository.saveAndFlush(newAttendance);
    }

    public JSONObject sendDataForValidation(String studentNum, String location) throws ParseException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("student_num", studentNum);
        params.add("img_loc", location);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "http://127.0.0.1:8000/val",
                HttpMethod.POST,
                entity,
                String.class
        );
        String body = response.getBody();
        JSONParser jsonParser = new JSONParser();
        JSONObject valResult = (JSONObject) jsonParser.parse(body);

        if (valResult.get("validation_result").equals("False")) {
            // 실패했을경우 반환 필요하면 추가
        }
        return valResult;
    }
}
