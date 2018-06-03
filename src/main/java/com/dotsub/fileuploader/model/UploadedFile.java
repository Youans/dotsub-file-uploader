/*
 * Copyright 2018 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.dotsub.fileuploader.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * The uploaded file class has the metadata of the orginal file uploaded plus
 * the new location where It was uploaded to
 *
 * @author Youans Ezzat
 */
@Entity public class UploadedFile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter @Setter private long id;

    @Getter @Setter private String orginalName;

    @Getter @Setter private Long sizeInBytes;

    @Getter @Setter private String contentType;

    @Getter @Setter private String location;

    @ManyToOne(fetch = FetchType.LAZY) @Getter @Setter private FileDetails fileDetails;

    /**
     * Instantiates a new Uploaded file.
     *
     * @param orginalName the orginal name
     * @param sizeInBytes the size in bytes
     * @param contentType the content type
     * @param location    the location
     */
    public UploadedFile(String orginalName, Long sizeInBytes, String contentType, String location) {
        this.orginalName = orginalName;
        this.sizeInBytes = sizeInBytes;
        this.contentType = contentType;
        this.location = location;
    }
}
