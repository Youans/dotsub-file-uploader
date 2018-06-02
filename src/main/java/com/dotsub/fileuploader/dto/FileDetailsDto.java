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

import com.dotsub.fileuploader.model.UploadedFile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author Youans Ezzat
 */
public class FileDetailsDto {

    @Getter @Setter private long id;

    @Getter @Setter private String title;

    @Getter @Setter private String description;

    @Getter @Setter private Date creationDate;

    private Set<UploadedFile> uploadedFiles;

}
