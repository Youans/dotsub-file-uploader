/*
 * Copyright 2018 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.dotsub.fileuploader.service;

import com.dotsub.fileuploader.dto.FileDetailsDto;
import com.dotsub.fileuploader.model.FileDetails;
import com.dotsub.fileuploader.model.UploadedFile;
import com.dotsub.fileuploader.repository.FileRepository;
import com.dotsub.fileuploader.service.storage.StorageStrategyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Youans Ezzat
 */
@Service public class FileService {

    static Logger logger = LoggerFactory.getLogger(FileService.class);

    private StorageStrategyService storageService;

    private FileRepository fileRepository;


    public FileService(@Qualifier("localServerStorageStrategyService")  StorageStrategyService storageService, FileRepository fileRepository) {
        this.storageService = storageService;
        this.fileRepository = fileRepository;
    }


    public void uploadAndSave(MultipartFile file, String title, String description, Date creationDate) throws IOException,IllegalArgumentException {
        logger.info("title " + title + " desc " + description + "creationDate " + creationDate);
        if(file==null || StringUtils.isEmpty(title) || StringUtils.isEmpty(description) || creationDate == null) {
            throw new IllegalArgumentException("At least one parameter is invalid or not supplied");
        }

        String location=storageService.store(file);
        UploadedFile uploadedFile=new UploadedFile(file.getOriginalFilename(),file.getSize(),file.getContentType(),location);
        FileDetails fileDetails=new FileDetails(title,description,creationDate,uploadedFile);
        uploadedFile.setFileDetails(fileDetails);

        fileRepository.save(fileDetails);

    }

    public List<FileDetails> listAll() {
        return fileRepository.findAll();
    }
}
