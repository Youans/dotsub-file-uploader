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
 * @author Youans Ezzat
 */
@Entity public class UploadedFile {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Getter @Setter private long id;

    @Getter @Setter private String orginalName;

    @Getter @Setter private Long sizeInBytes;

    @Getter @Setter private String contentType;

    @Getter @Setter private String location;

    @ManyToOne(fetch = FetchType.LAZY) @Getter @Setter private FileDetails fileDetails;

    public UploadedFile(String orginalName, Long sizeInBytes, String contentType, String location) {
        this.orginalName = orginalName;
        this.sizeInBytes = sizeInBytes;
        this.contentType = contentType;
        this.location = location;
    }
}
