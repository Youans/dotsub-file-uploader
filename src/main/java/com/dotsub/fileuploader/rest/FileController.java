package com.dotsub.fileuploader.rest;

import com.dotsub.fileuploader.service.FileService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Date;

/**
 * @author Youans Ezzat
 */
@RestController public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping public ResponseEntity upload(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "creationDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date creationDate
    ) {
        try {
            fileService.upload(file.getBytes(),title,description,creationDate);
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

}
