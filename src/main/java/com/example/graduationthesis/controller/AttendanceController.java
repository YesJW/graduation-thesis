package com.example.graduationthesis.controller;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.example.graduationthesis.data.dto.ImgDto;
import com.example.graduationthesis.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.imgscalr.Scalr;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
            @RequestPart(value = "faceImage") MultipartFile faceImage) throws IOException, ParseException, MetadataException {

        ImgDto imgDto = new ImgDto(
                UUID.randomUUID().toString(),
                faceImage.getOriginalFilename()
        );

//        Metadata metadata = getMetadata(faceImage.getInputStream());
        int orientation = 8;//getOrientation(metadata);
        BufferedImage bufferedImage = ImageIO.read(faceImage.getInputStream());
        if (orientation != 1)
            bufferedImage = rotateImage(bufferedImage, orientation);

        File saveFile = new File(location+"/temp_image" + File.separator +imgDto.getUuid() + "_" + imgDto.getFileName());

        ImageIO.write(bufferedImage, "jpg", saveFile);

        JSONObject responseJson = attendanceService.sendDataForValidation("val",studentNum, location+"/temp_image/"+saveFile.getName());

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

    public int getOrientation(Metadata metadata) throws MetadataException {
        int orientation = 1;
        Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
        if(directory != null && directory.containsTag(ExifIFD0Directory.TAG_ORIENTATION))
            orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);

        return orientation;
    }

    public BufferedImage rotateImage(BufferedImage bufferedImage, int orientation) {
        if(orientation == 6 )      return Scalr.rotate(bufferedImage, Scalr.Rotation.CW_90);
        else if (orientation == 3) return Scalr.rotate(bufferedImage, Scalr.Rotation.CW_180);
        else if(orientation == 8)  return Scalr.rotate(bufferedImage, Scalr.Rotation.CW_270);
        else                       return bufferedImage;
    }

}
