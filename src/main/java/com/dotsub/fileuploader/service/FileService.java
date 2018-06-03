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
 * This class contains the business logic code of uploading file
 * works as a router for storing via strategies check @{@link StorageStrategyService}
 *
 * @see StorageStrategyService
 * @author Youans Ezzat
 */
@Service public class FileService {


    private StorageStrategyService storageService;

    private FileRepository fileRepository;

    /**
     * Instantiates a new File service.
     *
     * @param storageService the storage service
     * @param fileRepository the file repository
     */
    public FileService(@Qualifier("localServerStorageStrategyService")  StorageStrategyService storageService, FileRepository fileRepository) {
        this.storageService = storageService;
        this.fileRepository = fileRepository;
    }

    /**
     * Pass a file to proper storage strategy in order to upload get the
     * link to file and then pack an object of {@link FileDetails}
     * with child {@link UploadedFile} and pass it to {@link FileRepository}
     * in order to save.
     *
     * @param file         the file type {@link MultipartFile}
     * @param title        the title type {@link String}
     * @param description  the description type {@link String}
     * @param creationDate the creation date type {@link Date}
     * @throws IOException              If IO excpetions thrown
     * @throws IllegalArgumentException If any parameter was invalid
     */
    public void uploadAndSave(MultipartFile file, String title, String description, Date creationDate) throws IOException,IllegalArgumentException {

        if(file==null || StringUtils.isEmpty(title) || StringUtils.isEmpty(description) || creationDate == null) {
            throw new IllegalArgumentException("At least one parameter is invalid or not supplied");
        }

        String location=storageService.store(file);
        UploadedFile uploadedFile=new UploadedFile(file.getOriginalFilename(),file.getSize(),file.getContentType(),location);
        FileDetails fileDetails=new FileDetails(title,description,creationDate,uploadedFile);
        uploadedFile.setFileDetails(fileDetails);

        fileRepository.save(fileDetails);

    }

}
