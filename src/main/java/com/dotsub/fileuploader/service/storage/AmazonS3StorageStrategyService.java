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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Dummy implementation of Amazon S3 file upload strategy
 *
 * @author Youans Ezzat
 */
@Service
public class AmazonS3StorageStrategyService implements StorageStrategyService {

    /**
     * The Logger.
     */
    static final Logger logger = LoggerFactory.getLogger(AmazonS3StorageStrategyService.class);

    public String store(MultipartFile multipartFile) {
            logger.info("Amazon S3 Implmentation...");
            return null;
    }
}
