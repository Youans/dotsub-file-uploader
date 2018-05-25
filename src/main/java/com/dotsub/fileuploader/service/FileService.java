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

import com.dotsub.fileuploader.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * @author Youans Ezzat
 */
@Service public class FileService {

    private FileRepository fileRepository;

    public FileService() {
        this.fileRepository = fileRepository;
    }

    public void upload(byte[] bytes, String title, String description, Date creationDate) throws IOException {



    }

}
