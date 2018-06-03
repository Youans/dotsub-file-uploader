/*
 * Copyright 2018 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.dotsub.fileuploader.dto;

import com.dotsub.fileuploader.model.FileDetails;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * The type Uploaded file dto.
 *
 * @author Youans Ezzat
 */
public class UploadedFileDto {

    @Getter @Setter private long id;

    @Getter @Setter private String orginalName;

    @Getter @Setter private Long sizeInBytes;

    @Getter @Setter private String contentType;

    @Getter @Setter private String location;

   @Getter @Setter private FileDetails fileDetails;

}
