/*
 * Copyright 2018 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.dotsub.fileuploader.service.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Youans Ezzat
 */
@Service
public class LocalServerStorageStrategyService implements StorageStrategyService {
    static final Logger logger = LoggerFactory.getLogger(AmazonS3StorageStrategyService.class);

    private String uploadLocation;
    private LocalServerStorageStrategyService(@Value("${upload.location}") String uploadLocation){this.uploadLocation=uploadLocation;}

    public String store(MultipartFile multipartFile) throws IOException {
        logger.info("Storing via Local Server");
        final StringBuilder fileLocation=new StringBuilder(uploadLocation);

        fileLocation.append(System.nanoTime()).append("_").append(multipartFile.getOriginalFilename().replaceAll(" ","_"));
        logger.info("fileLocation "+fileLocation);
        File file = new File(fileLocation.toString());
        file.createNewFile();
        try (FileOutputStream fos = new FileOutputStream(file);) {
            fos.write(multipartFile.getBytes());
        }
        return fileLocation.toString();
    }
}
