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

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Youans Ezzat
 */
public interface StorageStrategyService {
    public String store(MultipartFile file) throws IOException;
}
