package com.dotsub.fileuploader.rest;

import com.dotsub.fileuploader.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Date;

/**
 * @author Youans Ezzat
 */
@Api(description = "Contains file endpoints", produces = "application/json",tags = {"File"})
@RequestMapping(value = "/api/file")
@Validated
@RestController public class FileController {

    static Logger logger = LoggerFactory.getLogger(FileService.class);

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    @ApiOperation(value = "Upload file endpoint", notes = " A status code of "
            + HttpURLConnection.HTTP_NO_CONTENT + " is returned if no exception happened.")
    @PostMapping public ResponseEntity upload(
            @Valid @NotNull @RequestParam(value = "file") MultipartFile file,
            @Valid @NotBlank @RequestParam(value = "title") String title,
            @Valid @NotBlank @RequestParam(value = "description") String description,
            @Valid @NotNull @Past @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(value = "creationDate") Date creationDate) {
        try {
            fileService.uploadAndSave(file, title, description, creationDate);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
