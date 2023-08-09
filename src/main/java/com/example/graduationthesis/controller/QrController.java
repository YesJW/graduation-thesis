package com.example.graduationthesis.controller;

import com.example.graduationthesis.data.dto.UserResponseDto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class QrController {

    @GetMapping("qr")
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
}
