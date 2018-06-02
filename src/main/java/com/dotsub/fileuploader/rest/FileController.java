package com.dotsub.fileuploader.rest;

import com.dotsub.fileuploader.dto.FileDetailsDto;
import com.dotsub.fileuploader.dto.mappers.FileDetailsMapper;
import com.dotsub.fileuploader.model.FileDetails;
import com.dotsub.fileuploader.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;

/**
 * @author Youans Ezzat
 */
@Api(description = "Contains file endpoints", produces = "application/json",tags = {"File"})
@RequestMapping(value = "/file")
@RestController public class FileController {

    static Logger logger = LoggerFactory.getLogger(FileService.class);

    private FileService fileService;

    FileDetailsMapper fileDetailsMapper;

    public FileController(FileService fileService, FileDetailsMapper fileDetailsMapper) {
        this.fileService = fileService;
        this.fileDetailsMapper = fileDetailsMapper;
    }
    @ApiOperation(value = "Upload file endpoint", notes = " A status code of "
            + HttpURLConnection.HTTP_NO_CONTENT + " is returned if no exception happened.")
    @PostMapping public ResponseEntity upload(
            @RequestParam(value = "file") MultipartFile file, @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "creationDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE_TIME) Date creationDate) {
        try {
            fileService.uploadAndSave(file, title, description, creationDate);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @ApiOperation(value = "List all files.")
    @GetMapping public ResponseEntity list() {
        List<FileDetailsDto> fileDetailsDtos = fileDetailsMapper.toFileDetailsDtos(fileService.listAll());
        return ResponseEntity.ok(fileDetailsDtos);
    }
}
