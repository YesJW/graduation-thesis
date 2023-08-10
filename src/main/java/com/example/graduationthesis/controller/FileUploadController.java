package com.example.graduationthesis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestPart;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@RestController
public class FileUploadController {

    // 파일 업로드 경로 설정 (저장할 경로 application.properties에 수정)
    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    private final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/upload")
    public String handleFileUpload(@RequestPart("file") MultipartFile file, @RequestPart("fileName") String fileName ) {
        LOGGER.info("호출됨");

        try {
            // 파일명 생성 (UUID를 사용하여 고유한 파일명 생성)
            // 파일 저장 경로 생성
            String filePath = uploadDir + File.separator + fileName;

            // 파일 저장
            Files.copy(file.getInputStream(), new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);

            // 파일 업로드 성공 메시지 반환
            return "File uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            // 파일 업로드 실패 메시지 반환
            return "File upload failed!";
        }
    }

    private String getFileExtension(String filename) {
        // 파일명에서 확장자 추출
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex >= 0) {
            return filename.substring(dotIndex);
        }
        return "";
    }
}
