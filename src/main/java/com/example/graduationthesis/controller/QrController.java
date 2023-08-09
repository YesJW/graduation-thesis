package com.example.graduationthesis.controller;

import com.example.graduationthesis.data.dto.UserResponseDto;
import com.example.graduationthesis.repository.LectureRepository;
import com.example.graduationthesis.service.LectureService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QrController {
    private LectureService lectureService;
    @Autowired
    public QrController(LectureService lectureService){
        this.lectureService = lectureService;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(QrController.class);

    @GetMapping("/qr")
    public Object createQr(@RequestParam String url, HttpSession session) throws WriterException, IOException {
        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute("user");
        if (userResponseDto.getRole().equals("professor")) {
            int width = 200;
            int height = 200;
            BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);

            try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
                MatrixToImageWriter.writeToStream(matrix, "PNG", out);
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(out.toByteArray());
            }
        }
        else return null;
    }

    @PostMapping("/qrCheck")
    public List<String> check_Lecture(@RequestParam String id){
        LOGGER.info("qr체크 강의 목록 확인 ");
        List<String> lectureList = lectureService.getStudentLectureList(id);

        if(!lectureList.isEmpty()) {
            return lectureList;
        }
        return null;

    }
}
