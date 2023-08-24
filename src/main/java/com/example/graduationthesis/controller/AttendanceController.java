package com.example.graduationthesis.controller;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.example.graduationthesis.data.dto.ImgDto;
import com.example.graduationthesis.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

        ImgDto imgDto = new ImgDto(
                UUID.randomUUID().toString(),
                faceImage.getOriginalFilename()
        );

        Metadata metadata = getMetadata(faceImage.getInputStream());


        File saveFile = new File("temp_image" + File.separator +imgDto.getUuid() + "_" + imgDto.getFileName());
        faceImage.transferTo(saveFile);

        JSONObject responseJson = attendanceService.sendDataForValidation("val","20184114", location+"/temp_image/"+saveFile.getName());

        if (responseJson.get("validation_result").equals("True")) {
            attendanceService.saveAttendance(studentNum, lectureCode);
        }

        return responseJson.toJSONString();
    }

    public Metadata getMetadata(InputStream inputStream) {
        try {
            return ImageMetadataReader.readMetadata(inputStream);
        } catch (ImageProcessingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
