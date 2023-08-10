package com.example.graduationthesis.controller;

import com.example.graduationthesis.data.dto.ImgDto;
import com.example.graduationthesis.service.AttendanceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Value("${spring.servlet.multipart.location}")
    private String location;

    @PostMapping("/attendance")
    public String attendance(
            @RequestPart(value = "studentNum") String studentNum,
            @RequestPart(value = "lectureCode") String lectureCode,
            @RequestPart(value = "faceImage") MultipartFile faceImage) throws IOException, ParseException {
        // TODO: 2023/07/21 얼굴 인증 서버에 사진과 학번을 넘기고 호출하기

        ImgDto imgDto = new ImgDto(
                UUID.randomUUID().toString(),
                faceImage.getOriginalFilename()
        );
        File saveFile = new File(imgDto.getUuid() + "_" + imgDto.getFileName());
        faceImage.transferTo(saveFile);

//        JSONObject responseJson = attendanceService.sendDataForValidation("20184114", location+"/"+saveFile.getName());
        JSONObject responseJson = attendanceService.sendDataForValidation("20184114", location+"/"+saveFile.getName());

        if (responseJson.get("validation_result").equals("True")) {
            attendanceService.saveAttendance(studentNum, lectureCode);
        }

        return responseJson.toJSONString();
    }
}
