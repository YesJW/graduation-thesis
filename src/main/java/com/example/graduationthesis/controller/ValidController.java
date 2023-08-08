package com.example.graduationthesis.controller;

import com.example.graduationthesis.data.dto.ImgDto;
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
public class ValidController {
    @Value("${spring.servlet.multipart.location}")
    private String location;

    @PostMapping("/face-recog")
    public String faceRecognition(
            @RequestPart(value = "studentNum") String studentNum,
            @RequestPart(value = "faceImage") MultipartFile faceImage) throws IOException, ParseException {
        // TODO: 2023/07/21 얼굴 인증 서버에 사진과 학번을 넘기고 호출하기

        ImgDto imgDto = new ImgDto(
                UUID.randomUUID().toString(),
                faceImage.getOriginalFilename()
        );

        File saveFile = new File(imgDto.getUuid() + "_" + imgDto.getFileName());
        faceImage.transferTo(saveFile);

        String response = sendDataForValidation(studentNum, location+"/"+saveFile.getName());

        return response;
    }

    private String sendDataForValidation(String studentNum, String location) throws ParseException {
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
        return valResult.toJSONString();
    }
}
